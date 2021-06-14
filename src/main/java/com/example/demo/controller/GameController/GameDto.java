package com.example.demo.controller.GameController;

public class GameDto {
    private long gameId;
    private String name;
    private boolean started;

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public UserDto[] getUsers() {
        return users;
    }

    public void setUsers(UserDto[] users) {
        this.users = users;
    }

    private UserDto[] users;
}
