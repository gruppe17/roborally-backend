package com.example.demo.service.implementations;

import com.example.demo.dal.implementations.GameDao;
import com.example.demo.dal.interfaces.IBoardDao;
import com.example.demo.dal.interfaces.IGameDao;
import com.example.demo.dal.interfaces.IPlayerDao;
import com.example.demo.dal.interfaces.ISpaceDao;
import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.Game;
import com.example.demo.service.interfaces.IGameService;

import java.util.Collection;

public class GameService implements IGameService {

    private final GameDao gameDao;

    public GameService(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    @Override
    public Game createGame() throws ServiceException, DaoException {
        return null;
    }

    @Override
    public boolean removeGame(long gameId) throws ServiceException, DaoException {
        return false;
    }

    @Override
    public Game getGame(long gameID) throws ServiceException, DaoException {
        return null;
    }

    @Override
    public Collection<Game> getAllGames() throws ServiceException, DaoException {
        return null;
    }

    @Override
    public boolean setBoard(long gameID) throws ServiceException, DaoException {
        return false;
    }

    @Override
    public boolean getBoard(long gameID) throws ServiceException, DaoException {
        return false;
    }

    @Override
    public boolean joinGame(long gameID, long userID) throws ServiceException, DaoException {
        return false;
    }

    @Override
    public boolean leaveGame(long userID)  throws ServiceException, DaoException {
        return false;
    }

    @Override
    public boolean editGameName(long gameID, String name) throws ServiceException, DaoException {
        return false;
    }
}
