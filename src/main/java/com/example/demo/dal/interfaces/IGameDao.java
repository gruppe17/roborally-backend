package com.example.demo.dal.interfaces;

import com.example.demo.exceptions.DaoException;
import com.example.demo.model.Game;

import java.util.List;


public interface IGameDao {
	Game getGame(int gameId) throws DaoException;

	List<Game> getAllGames() throws DaoException;

	int createGame(Game game) throws DaoException;

	void updateGame(Game game, int gameId) throws DaoException;

	boolean deleteGame(int gameId) throws DaoException;
}

