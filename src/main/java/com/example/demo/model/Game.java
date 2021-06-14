package com.example.demo.model;

import java.util.List;

public class Game {
	private long gameId;
	private String gameName;

	public boolean isStarted() {
		return started;
	}

	public List<User> getUsers() {
		return users;
	}

	private boolean started;
	private List<User> users;

	public String getGameName() {
		return gameName;
	}

	public boolean setGameName(String gameName) {
		this.gameName = gameName;
		return true;
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
