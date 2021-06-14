package com.example.demo.model;

import java.util.LinkedList;
import java.util.List;

public class Game {
	private long gameId;
	private String gameName;

	public boolean isStarted() {
		return started;
	}

	public List<Long> getUsers() {
		return users;
	}

	private boolean started;
	private List<Long> users;

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
		users = new LinkedList<>();
	}

	public boolean addUser(long userId){
		return users.add(userId);
	}

	public boolean removeUser(long userId){
		return users.remove(userId);
	}


	public long getGameId() {
		return gameId;
	}
	public void setGameId(long gameId) {
		this.gameId = gameId;
	}
}
