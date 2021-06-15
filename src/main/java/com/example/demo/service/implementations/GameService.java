package com.example.demo.service.implementations;

import com.example.demo.dal.implementations.GameDao;
import com.example.demo.dal.implementations.UserDao;
import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.Game;
import com.example.demo.service.interfaces.IGameService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GameService implements IGameService {

    private final GameDao gameDao;

    public GameService(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    @Override
    public int createGame() throws ServiceException, DaoException {
        Game game = new Game(0, "Default Game");
        int id = gameDao.createGame(game);
        return id;
    }

    @Override
    public boolean removeGame(int gameId) throws ServiceException, DaoException {
    	return gameDao.deleteGame(gameId);
    }

    @Override
    public Game getGame(int gameID) throws ServiceException, DaoException {
        return gameDao.getGame(gameID);
    }

    @Override
    public Collection<Game> getAllGames() throws ServiceException, DaoException {
        return gameDao.getAllGames();
    }

    @Override
    public boolean joinGame(int gameID, int userID) throws ServiceException, DaoException {
        return gameDao.getGame(gameID).addUser(userID);
    }

    @Override
    public boolean leaveGame(int gameId, int userID)  throws ServiceException, DaoException {
        Game game = gameDao.getGame(gameId);
        if (game == null) return false;
        return game.removeUser(userID);
    }

    @Override
    public boolean editGameName(int gameID, String name) throws ServiceException, DaoException {
        Game game = gameDao.getGame(gameID);
        game.setGameName(name);
        gameDao.updateGame(game, gameID);
        return game.getGameName().equals(name);
    }
}
