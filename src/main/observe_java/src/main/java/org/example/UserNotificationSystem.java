package org.example;
import com.google.common.eventbus.Subscribe;

public class UserNotificationSystem {
    private String username;
    private GameRecommendationModel recommendationModel;

    public UserNotificationSystem(String username, GameRecommendationModel recommendationModel) {
        this.username = username;
        this.recommendationModel = recommendationModel;
    }

    @Subscribe
    public void onGameRecommendation(GameRecommendationEvent recommendationEvent) {
        PlayerInfo playerInfo = recommendationEvent.getPlayerInfo();
        String recommendedGame = recommendationModel.recommendGame(playerInfo);

        if (playerInfo.getUsername().equals(username)) {
            System.out.println(username + " 收到推荐游戏消息：" + recommendedGame);
        }
    }

}