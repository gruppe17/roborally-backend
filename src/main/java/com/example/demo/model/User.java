package com.example.demo.model;

public class User {

	private int userId;
	private String userName;
	private Integer currentGameId;

	public User(int userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Integer getCurrentGameId() {
		return currentGameId;
	}
	public void setCurrentGameId(Integer currentGameId) {
		this.currentGameId = currentGameId;
	}
}
