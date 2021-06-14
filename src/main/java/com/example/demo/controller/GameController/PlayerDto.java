package com.example.demo.controller.GameController;

public class PlayerDto {
	private Integer gameId;
	private Integer playerId;
	private String playerName;
	private String playerColor;
	private Integer x;
	private Integer y;

	public Integer getgameId() {
		return gameId;
	}

	public void setgameId(Integer gameId) {
		this.gameId = gameId;
	}

	public Integer getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPlayerColor() {
		return playerColor;
	}

	public void setPlayerColor(String playerColor) {
		this.playerColor = playerColor;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}
}
