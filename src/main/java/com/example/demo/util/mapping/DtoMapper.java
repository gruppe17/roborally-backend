package com.example.demo.util.mapping;

import com.example.demo.controller.GameController.*;
import com.example.demo.exceptions.MappingException;
import com.example.demo.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DtoMapper implements IDtoMapper {
	public PlayerDto convertToDto(Player player) throws MappingException {
		if(player == null){
			throw new MappingException("Player was null");
		}
		PlayerDto playerDto = new PlayerDto();
		playerDto.setgameId(player.board.getGameId());
		playerDto.setPlayerId(player.getPlayerId());
		playerDto.setPlayerName(player.getName());
		playerDto.setPlayerColor(player.getColor());

		if (player.getSpace() != null) {
			playerDto.setX(player.getSpace().x);
			playerDto.setY(player.getSpace().y);
		}

		return playerDto;
	}


	public BoardDto convertToDto(Board board) throws MappingException {
		if(board == null){
			throw new MappingException("Board was null");
		}
		BoardDto boardDto = new BoardDto();
		boardDto.setgameId(board.getGameId());
		boardDto.setBoardName(board.boardName);
		boardDto.setHeight(board.height);
		boardDto.setWidth(board.width);

		if (board.getCurrentPlayer() != null) {
			boardDto.setCurrentPlayerDto(convertToDto(board.getCurrentPlayer()));
		}

		if (board.getPlayersNumber() > 0) {
			boardDto.setPlayerDtos(new PlayerDto[board.getPlayersNumber()]);
			for (int i = 0; i < board.getPlayersNumber(); i++) {
				boardDto.getPlayerDtos()[i] = convertToDto(board.getPlayer(i));
			}
		}

		if (board.getSpaces().length > 0 && board.getSpaces()[0].length > 0) {
			boardDto.setSpaceDtos(new SpaceDto[board.getSpaces().length][board.getSpaces()[0].length]);
			for (int i = 0; i < board.getSpaces().length; i++) {
				for (int j = 0; j < board.getSpaces()[i].length; j++) {
					boardDto.getSpaceDtos()[i][j] = convertToDto(board.getSpace(i, j));
				}
			}
		}

		return boardDto;
	}

	public SpaceDto convertToDto(Space space) throws MappingException {
		if(space == null){
			throw new MappingException("Space was null");
		}
		SpaceDto spaceDto = new SpaceDto();
		spaceDto.setX(space.x);
		spaceDto.setY(space.y);
		if (space.getPlayer() != null) {
			spaceDto.setPlayerId(space.getPlayer().getPlayerId());
		}
		return spaceDto;
	}

	@Override
	public GameDto convertToDto(Game game) throws MappingException {
		if(game == null){
			throw new MappingException("Game was null");
		}

		GameDto gameDto = new GameDto();
		gameDto.setGameId(game.getGameId());
		gameDto.setName(game.getGameName());
		gameDto.setStarted(game.isStarted());
		gameDto.setUsers(game.getUsers());
		return gameDto;
	}

	@Override
	public UserDto convertToDto(User user) throws MappingException {
		if(user == null){
			throw new MappingException("User was null");
		}
		UserDto userDto = new UserDto();
		userDto.setUserId(user.getUserId());
		userDto.setUserName(user.getUserName());
		int gameId = user.getCurrentGameId();
		userDto.setCurrentGame(user.getCurrentGameId() == null ? 0 : gameId);
		return userDto;

	}

	public Board convertToEntity(BoardDto boardDto) throws MappingException {
		Board board = new Board(boardDto.getWidth(), boardDto.getHeight(), boardDto.getBoardName());
		board.setGameId(boardDto.getgameId());

		for (PlayerDto playerDto: boardDto.getPlayerDtos()
		     ) {
			board.addPlayer(convertToEntity(playerDto, board));
		}

		return board;
	}

	public Space convertToEntity(SpaceDto spaceDto, Board board) {
		return new Space(board, spaceDto.getX(), spaceDto.getY());
	}

	public Player convertToEntity(PlayerDto playerDto, Board board) throws MappingException {
		if (playerDto.getgameId() == null) { //We cant have a player without a board id
			throw new MappingException("PlayerDto did not contain a board id");
		}
		if (board == null) { //Incase the provided board id is invalid
			throw new MappingException("Board was null when trying to convert PlayerDto to Player");
		}
		if (playerDto.getPlayerId() == null) { //If we have not provided a player id, we are creating a new player
			return new Player(board, playerDto.getPlayerColor(), playerDto.getPlayerName());
		}
		return null;
	}

	@Override
	public Game convertToEntity(GameDto gameDto) throws MappingException {
		Game game = new Game(gameDto.getGameId(), gameDto.getName());

		for (int userDto: game.getUsers()
		) {
			game.addUser(userDto);
		}
		game.setStarted(gameDto.isStarted());
		return game;
	}

	@Override
	public User convertToEntity(UserDto userDto, Game game) throws MappingException {
		return new User(userDto.getUserId(), userDto.getUserName(), userDto.getCurrentGame());
	}
}
