package com.example.demo.dal.interfaces;

import com.example.demo.exceptions.DaoException;
import com.example.demo.model.Game;


public interface IGameDao {
	Game getGame(long gameId) throws DaoException;

	long createGame(Game game) throws DaoException;

	void updateGame(Game game, long gameId) throws DaoException;

	boolean deleteGame(long gameId) throws DaoException;
}

