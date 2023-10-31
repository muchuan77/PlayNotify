package org.example;
import com.google.gson.Gson;


import java.io.*;
import java.util.*;
import org.example.GameRecommendationModel;
import org.example.MessageCenter;
import org.example.PlayerInfo;
import org.example.UserNotificationSystem;

public class GamePushNotificationSystem {
    public static void main(String[] args) {
        // 创建消息中心
        MessageCenter messageCenter = new MessageCenter();

        // 创建玩家信息
        PlayerInfo player1 = new PlayerInfo("玩家1", Arrays.asList("动作", "冒险"));
        PlayerInfo player2 = new PlayerInfo("玩家2", Arrays.asList("策略", "RPG"));
        // 继续扩展测试数据
        PlayerInfo player3 = new PlayerInfo("玩家3", Arrays.asList("射击", "冒险"));
        PlayerInfo player4 = new PlayerInfo("玩家4", Arrays.asList("射击", "RPG"));
        PlayerInfo player5 = new PlayerInfo("玩家5", Arrays.asList("策略", "冒险"));
        PlayerInfo player6 = new PlayerInfo("玩家6", Arrays.asList("恐怖", "射击"));

        // 创建机器学习模型
        GameRecommendationModel model = new GameRecommendationModel();

        // 创建用户通知系统并订阅
        UserNotificationSystem user1System = new UserNotificationSystem("玩家1", model);
        UserNotificationSystem user2System = new UserNotificationSystem("玩家2", model);
        // 继续创建用户通知系统
        UserNotificationSystem user3System = new UserNotificationSystem("玩家3", model);
        UserNotificationSystem user4System = new UserNotificationSystem("玩家4", model);
        UserNotificationSystem user5System = new UserNotificationSystem("玩家5", model);
        UserNotificationSystem user6System = new UserNotificationSystem("玩家6", model);

        messageCenter.subscribe(user1System);
        messageCenter.subscribe(user2System);
        // 继续订阅用户通知系统
        messageCenter.subscribe(user3System);
        messageCenter.subscribe(user4System);
        messageCenter.subscribe(user5System);
        messageCenter.subscribe(user6System);

        // 模拟游戏推荐事件
        String gameRecommendation1 = "动作冒险游戏";
        String gameRecommendation2 = "策略游戏";
        // 继续发送游戏推荐事件
        String gameRecommendation3 = "射击RPG";
        String gameRecommendation4 = "战略战争游戏";
        String gameRecommendation5 = "动作恐怖冒险";
        String gameRecommendation6 = "模拟射击";

        messageCenter.sendGameRecommendation(player1, gameRecommendation1);
        messageCenter.sendGameRecommendation(player2, gameRecommendation2);
        // 继续发送游戏推荐事件
        messageCenter.sendGameRecommendation(player3, gameRecommendation3);
        messageCenter.sendGameRecommendation(player4, gameRecommendation4);
        messageCenter.sendGameRecommendation(player5, gameRecommendation5);
        messageCenter.sendGameRecommendation(player6, gameRecommendation6);

        // 用户输入接口
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入您的用户名: ");
        String username = scanner.nextLine();
        System.out.print("请输入您喜欢的游戏标签（用逗号分隔，例如：动作,冒险）: ");
        String tagsInput = scanner.nextLine();
        List<String> favoriteTags = Arrays.asList(tagsInput.split(","));
        PlayerInfo userPlayerInfo = new PlayerInfo(username, favoriteTags);

        String recommendation = model.recommendGame(userPlayerInfo);
        messageCenter.sendGameRecommendation(userPlayerInfo, recommendation);


        // 调用 Python 代码并接收其推荐结果
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("D:/Python3.11.2/python.exe", "F:/java.2022.muchuan/observers_last/src/main/observe_python/game_recommendation.py");
            processBuilder.directory(new File("F:/java.2022.muchuan/observers_last/src/main/observe_python"));
            processBuilder.redirectErrorStream(true);  // Redirect stderr to stdout
            Process process = processBuilder.start();

            // Convert the userPlayerInfo to JSON string and send it to the Python script
            String jsonInput = new Gson().toJson(userPlayerInfo);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            writer.write(jsonInput);
            writer.flush();
            writer.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            recommendation = reader.readLine();  // We expect a single line of output

            System.out.println("从Python脚本接收的游戏推荐：" + recommendation);

            // 使用消息中心发送游戏推荐
            messageCenter.sendGameRecommendation(userPlayerInfo, recommendation);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // 关闭输入流
        scanner.close();
    }
}
