package com.example.demo.service.interfaces;

import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.Game;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface IGameService {
	int createGame() throws ServiceException, DaoException;

	boolean removeGame(int gameId)  throws ServiceException, DaoException;

	Game getGame(int gameID) throws ServiceException, DaoException;

	Collection<Game> getAllGames() throws ServiceException, DaoException;

	boolean joinGame(int gameID, int userID) throws ServiceException, DaoException;

	boolean leaveGame(int gameId, int userID) throws ServiceException, DaoException;

	boolean editGameName(int gameID, String name) throws ServiceException, DaoException;

}
