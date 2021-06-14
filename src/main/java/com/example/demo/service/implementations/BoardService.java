package com.example.demo.service.implementations;

import com.example.demo.dal.interfaces.IBoardDao;
import com.example.demo.dal.interfaces.IPlayerDao;
import com.example.demo.dal.interfaces.ISpaceDao;
import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.Board;
import com.example.demo.model.Player;
import com.example.demo.model.Space;
import com.example.demo.service.interfaces.IBoardService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BoardService implements IBoardService {
    private final IBoardDao boardDao;
    private final ISpaceDao spaceDao;
    private final IPlayerDao playerDao;

    public BoardService(IBoardDao boardDao, ISpaceDao spaceDao, IPlayerDao playerDao) {
        this.boardDao = boardDao;
        this.spaceDao = spaceDao;
        this.playerDao = playerDao;
    }

    @Override
    public Board getBoard(int gameId) throws ServiceException, DaoException {
        if (gameId < 0) {
            throw new ServiceException("Invalid board id " + gameId, HttpStatus.BAD_REQUEST);
        }
        Board board = boardDao.getBoard(gameId);
        if (board == null) {
            throw new ServiceException("No board found with board id " + gameId, HttpStatus.NOT_FOUND);
        }

        return board;
    }

    @Override
    public void removeBoard(int gameId) throws ServiceException, DaoException {
        boardDao.deleteBoard(gameId);
    }

    @Override
    public int saveBoard(Board board) throws ServiceException, DaoException {
        int savedgameId = boardDao.createBoard(board);
        if (savedgameId < 0) {
            throw new ServiceException("BoardDao generated invalid gameId " + savedgameId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        spaceDao.createSpaces(savedgameId, board.getSpaces());
        return savedgameId;
    }

    @Override
    public Player getCurrentPlayer(int gameId) throws ServiceException, DaoException {
        if (gameId < 0) {
            throw new ServiceException("Invalid board id " + gameId, HttpStatus.BAD_REQUEST);
        }
        Board board = boardDao.getBoard(gameId);
        if (board == null) {
            throw new ServiceException("No board found for board id " + gameId, HttpStatus.NOT_FOUND);
        }
        Player player = board.getCurrentPlayer();
        if (player == null) {
            throw new ServiceException("The board with id " + gameId + " has no current player", HttpStatus.NOT_FOUND);
        }
        return player;
    }

    @Override
    public void setCurrentPlayer(int gameId, int playerId) throws ServiceException, DaoException {
        if (gameId < 0) {
            throw new ServiceException("Invalid board id " + gameId, HttpStatus.BAD_REQUEST);
        }
        if (playerId < 0) {
            throw new ServiceException("Invalid player id " + playerId, HttpStatus.BAD_REQUEST);
        }
        Board board = boardDao.getBoard(gameId);
        if (board == null) {
            throw new ServiceException("No board found for board id " + gameId, HttpStatus.NOT_FOUND);
        }
        Player player = playerDao.getPlayer(playerId);
        if (player == null) {
            throw new ServiceException("No player found for player id " + playerId, HttpStatus.NOT_FOUND);
        }
        board.setCurrentPlayer(player);
        boardDao.updateBoard(board, board.getGameId());
    }

    @Override
    public int addPlayer(int gameId, Player player) throws ServiceException, DaoException {
        if (player == null) {
            throw new ServiceException("Player to add to board was null", HttpStatus.BAD_REQUEST);
        }
        Board board = this.getBoard(gameId);
        int playerId = playerDao.addPlayer(gameId, player);
        board.addPlayer(player);
        boardDao.updateBoard(board, board.getGameId());
        return playerId;
    }

    @Override
    public void moveCurrentPlayer(int gameId, int x, int y) throws ServiceException, DaoException {
        Board board = this.getBoard(gameId);
        Player currentPlayer = board.getCurrentPlayer();
        if (currentPlayer == null) {
            throw new ServiceException("The board " + gameId + " has no current player", HttpStatus.BAD_REQUEST);
        }
        if (x < 0 || y < 0 || x > board.height || y > board.width) {
            throw new ServiceException("Space coordinates (" + x + "," + y + ") were invalid for board" + gameId, HttpStatus.BAD_REQUEST);
        }
        Space targetSpace = board.getSpace(x, y);
        if (targetSpace == null) {
            throw new ServiceException("Provided target space was not found", HttpStatus.NOT_FOUND);
        }
        currentPlayer.setSpace(targetSpace);
        boardDao.updateBoard(board, board.getGameId());
    }

    @Override
    public void movePlayer(Board board, int x, int y, int playerId) throws DaoException {
        Space space = board.getSpace(x, y);
        Player player = board.getPlayerById(playerId);
        if (space != null && player != null) {
            player.setSpace(space);
            boardDao.updateBoard(board, board.getGameId());
        }
    }

    @Override
    public void switchCurrentPlayer(int gameId) throws ServiceException, DaoException {
        Board board = this.getBoard(gameId);
        int amountOfPlayers = board.getPlayersNumber();
        if (amountOfPlayers <= 0) {
            throw new ServiceException("Trying to switch current player, but board has no players", HttpStatus.BAD_REQUEST);
        }
        int currentPlayerNumber = board.getPlayerNumber(board.getCurrentPlayer());
        int nextPlayerNumber = (currentPlayerNumber + 1) % amountOfPlayers;
        board.setCurrentPlayer(board.getPlayer(nextPlayerNumber));
        boardDao.updateBoard(board, board.getGameId());
    }


}
