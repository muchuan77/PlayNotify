package org.example;

import java.util.List;

public class PlayerInfo {
    private String username;
    private List<String> favoriteTags;

    public PlayerInfo(String username, List<String> favoriteTags) {
        this.username = username;
        this.favoriteTags = favoriteTags;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getFavoriteTags() {
        return favoriteTags;
    }
}