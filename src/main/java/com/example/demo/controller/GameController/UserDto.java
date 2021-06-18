package com.example.demo.controller.GameController;

public class UserDto {
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getCurrentGameId() {
		return currentGameId;
	}

	public void setCurrentGameId(int currentGameId) {
		this.currentGameId = currentGameId;
	}

	private int userId;
	private String userName;
	private int currentGameId;
}
