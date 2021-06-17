package com.example.demo.model;

import java.util.LinkedList;
import java.util.List;

public class Game {
	public final static int INVALID_GAMEID = -1;

	private int gameId;
	private String gameName;

	public void setStarted(boolean started) {
		this.started = started;
	}

	public boolean isStarted() {
		return started;
	}

	public List<Integer> getUsers() {
		return users;
	}

	private boolean started;
	private List<Integer> users;

	public String getGameName() {
		return gameName;
	}

	public boolean setGameName(String gameName) {
		this.gameName = gameName;
		return true;
	}

	public Game(int gameId, String gameName) {
		this.gameId = gameId;
		this.gameName = gameName;
		users = new LinkedList<>();
	}

	public boolean addUser(int userId){
		return users.add(userId);
	}

	public boolean removeUser(int userId){
		return users.remove(Integer.valueOf(userId));
	}

	public int getGameId() {
		return gameId;
	}
	public boolean setGameId(int gameId) {
		if (gameId == INVALID_GAMEID) return false;
		this.gameId = gameId;
		return true;
	}
}
