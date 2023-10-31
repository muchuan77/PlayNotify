package org.example;

public class GameRecommendationEvent {
    private PlayerInfo playerInfo;
    private String recommendation;

    public GameRecommendationEvent(PlayerInfo playerInfo, String recommendation) {
        this.playerInfo = playerInfo;
        this.recommendation = recommendation;
    }

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    public String getRecommendation() {
        return recommendation;
    }
}
