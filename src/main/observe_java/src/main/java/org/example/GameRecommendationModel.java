package org.example;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.Reader;

public class GameRecommendationModel {
    public String recommendGame(PlayerInfo playerInfo) {
        String recommendation = "策略游戏";  // 默认推荐

        // 获取玩家的喜好标签列表
        String favoriteTag = playerInfo.getFavoriteTags().get(0); // 取第一个标签作为示例

        // 基于标签提供推荐
        if ("动作".equals(favoriteTag)) {
            recommendation = "动作冒险游戏";
        }

        return recommendation;
    }

    private String recommendBasedOnTag(String tag) {
        if ("动作".equals(tag)) {
            return "动作冒险游戏";
        } else {
            return "策略游戏";
        }
    }
}
