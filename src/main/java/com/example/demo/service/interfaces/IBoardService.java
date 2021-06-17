package com.example.demo.service.interfaces;

import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.Board;
import com.example.demo.model.Game;
import com.example.demo.model.Player;


public interface IBoardService {
	Board getBoard(int gameId) throws ServiceException, DaoException;
	void removeBoard(int gameId) throws ServiceException, DaoException;

	int saveBoard(Game game, Board board) throws ServiceException, DaoException;

	Player getCurrentPlayer(int gameId) throws ServiceException, DaoException;

	void setCurrentPlayer(int gameId, int playerId) throws ServiceException, DaoException;

	int addPlayer(int gameId, Player player) throws ServiceException, DaoException;

	void moveCurrentPlayer(int gameId, int x, int y) throws ServiceException, DaoException;

	void movePlayer(Board board, int x, int y, int playerId) throws DaoException;

	void switchCurrentPlayer(int gameId) throws ServiceException, DaoException;
}
