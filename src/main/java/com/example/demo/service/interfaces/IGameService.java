package com.example.demo.service.interfaces;

import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.Game;

import java.util.Collection;

public interface IGameService {
	Game createGame() throws ServiceException, DaoException;

	boolean removeGame(long gameId)  throws ServiceException, DaoException;

	Game getGame(long gameID) throws ServiceException, DaoException;

	Collection<Game> getAllGames() throws ServiceException, DaoException;

	boolean setBoard(long gameID) throws ServiceException, DaoException;

	boolean getBoard(long gameID) throws ServiceException, DaoException;

	boolean joinGame(long gameID, long userID) throws ServiceException, DaoException;

	boolean leaveGame(long userID) throws ServiceException, DaoException;

	boolean editGameName(long gameID, String name) throws ServiceException, DaoException;

}
