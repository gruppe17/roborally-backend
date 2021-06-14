package com.example.demo.model;

public class Game {
	private long gameId;
	private String gameName;

	public boolean isStarted() {
		return started;
	}

	public User[] getUsers() {
		return users;
	}

	private boolean started;
	private User[] users;

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public Game(long gameId, String gameName) {
		this.gameId = gameId;
		this.gameName = gameName;
	}


	public long getGameId() {
		return gameId;
	}
	public void setGameId(long gameId) {
		this.gameId = gameId;
	}
}
