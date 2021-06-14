package com.example.demo.controller.GameController;

public class BoardDto {

	private Integer gameId;
	private String boardName;
	private int height;
	private int width;
	private SpaceDto[][] spaceDtos;
	private PlayerDto currentPlayerDto;
	private PlayerDto[] playerDtos;

	public PlayerDto[] getPlayerDtos() {
		return playerDtos;
	}

	public void setPlayerDtos(PlayerDto[] playerDtos) {
		this.playerDtos = playerDtos;
	}

	public void setgameId(Integer gameId) {
		this.gameId = gameId;
	}

	public PlayerDto getCurrentPlayerDto() {
		return currentPlayerDto;
	}

	public void setCurrentPlayerDto(PlayerDto currentPlayerDto) {
		this.currentPlayerDto = currentPlayerDto;
	}

	public SpaceDto[][] getSpaceDtos() {
		return spaceDtos;
	}

	public void setSpaceDtos(SpaceDto[][] spaceDtos) {
		this.spaceDtos = spaceDtos;
	}

	public int getgameId() {
		return gameId;
	}

	public void setgameId(int gameId) {
		this.gameId = gameId;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
