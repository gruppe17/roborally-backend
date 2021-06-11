package com.example.demo.service.interfaces;

import com.example.demo.model.Game;

import java.util.Collection;

public interface IActualGameService {
	Game createGame();

	boolean removeGame(long gameId);

	Game getGame(long gameID);

	Collection<Game> getAllGames();

	boolean setBoard(long gameID);

	boolean getBoard(long gameID);

	boolean joinGame(long gameID, long userID);

	boolean leaveGame(long userID);

	boolean editGameName(long gameID, String name);

}
