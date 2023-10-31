package org.example;

import com.google.common.eventbus.EventBus;

public class MessageCenter {
    private EventBus eventBus = new EventBus();

    public void subscribe(Object subscriber) {
        eventBus.register(subscriber);
    }

    public void unsubscribe(Object subscriber) {
        eventBus.unregister(subscriber);
    }

    public void sendGameRecommendation(PlayerInfo playerInfo, String recommendation) {
        GameRecommendationEvent event = new GameRecommendationEvent(playerInfo, recommendation);
        eventBus.post(event);
    }
}
