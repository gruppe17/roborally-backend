package com.example.demo.controller.GameController;

import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.MappingException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.Board;
import com.example.demo.model.Game;
import com.example.demo.model.Player;
import com.example.demo.model.Space;
import com.example.demo.service.interfaces.IBoardService;
import com.example.demo.service.interfaces.IGameService;
import com.example.demo.util.mapping.IDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@CrossOrigin
public class GameController {
    private final IBoardService boardService;
    private final IGameService gameService;
    private final IDtoMapper dtoMapper;

    public GameController(IBoardService boardService, IGameService gameService, IDtoMapper dtoMapper) {
        this.boardService = boardService;
        this.gameService = gameService;
        this.dtoMapper = dtoMapper;
    }

    /**
     * Endpoint for getting board information
     * @param gameId the id of the board we want to get
     * @return the board with the associated boardId we provided
     */
    @GetMapping("/game/get/{gameId}/board")
    public ResponseEntity<BoardDto> getBoard(@PathVariable("gameId") int gameId) throws ServiceException, MappingException, DaoException {
        Board board = boardService.getBoard(gameId);
        return new ResponseEntity<>(dtoMapper.convertToDto(board), HttpStatus.OK);
    }


    /**
     * Get current player of a board
     * @param gameId The board we want to get the current player from
     * @return Current player
     */
    @GetMapping("/game/get/{gameId}/board/currentPlayer")
    public ResponseEntity<PlayerDto> getCurrentPlayer(@PathVariable("gameId") int gameId) throws ServiceException, MappingException, DaoException {
        Player currentPlayer = boardService.getCurrentPlayer(gameId);
        return new ResponseEntity<>(dtoMapper.convertToDto(currentPlayer), HttpStatus.OK);
    }

    /**
     * Add a player to a board
     *
     * @param gameId   the id of the game we want to add a player to
     * @param playerDto the player we want to add to the board
     * @return the id of the player we have added
     */
    @PostMapping("/game/get/{gameId}/board//player")
    public ResponseEntity<Integer> addPlayer(@PathVariable("gameId") int gameId, @RequestBody PlayerDto playerDto) throws ServiceException, MappingException, DaoException {
        Board board = boardService.getBoard(gameId);
        Player player = dtoMapper.convertToEntity(playerDto, board);
        int playerId = boardService.addPlayer(gameId, player);
        return new ResponseEntity<>(playerId, HttpStatus.CREATED);
    }

    /**
     * Creates a new game
     * @return the identifier for the game
     * @throws ServiceException
     * @throws DaoException
     */
    @PostMapping("/game/new")
    public ResponseEntity<Long> createGame() throws ServiceException, DaoException {
        Game game = gameService.createGame();
        return new ResponseEntity<>(game.getGameId(), HttpStatus.CREATED);
    }

    /**
     * Removes current board and creates a new one for the given game
     * @param gameId
     * @return
     * @throws ServiceException
     * @throws DaoException
     */
    @PostMapping("/game/get/{gameId}/board/new")
    public ResponseEntity<Long> createBoard(@PathVariable("gameId") long gameId) throws ServiceException, DaoException {
       Game game = gameService.getGame(gameId);
       boardService.removeBoard((int)gameId);
       BoardDto boardDto = new BoardDto();
       boardDto.setBoardId((int)game.getGameId());
       Board board = dtoMapper.convertToEntity(boardDto);
       boardService.saveBoard(board);
       return new ResponseEntity<>(game.getGameId(), HttpStatus.CREATED);
    }

    /**
     * Move the current player
     *
     * @param gameId  the board on which we want to move the current player
     * @param spaceDto the space we want to move the player upon
     * @return Staus code indicating whether it went well or not
     */
    @PutMapping("/game/get/{gameId}/board/move")
    public ResponseEntity<Void> moveCurrentPlayer(@PathVariable("gameId") int gameId, @RequestBody SpaceDto spaceDto) throws ServiceException, DaoException {
        Board board = boardService.getBoard(gameId);
        Space space = dtoMapper.convertToEntity(spaceDto, board);
        boardService.moveCurrentPlayer(gameId, space.x, space.y);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Set the current player
     *
     * @param gameId  the board we are working upon
     * @param playerId the player we want to set as the current player
     * @return nothing
     */
    @PutMapping("/game/get/{gameId}/board/currentPlayer/{playerId}")
    public ResponseEntity<Void> setCurrentPlayer(@PathVariable("gameId") int gameId, @PathVariable("playerId") int playerId) throws ServiceException, DaoException {
        boardService.setCurrentPlayer(gameId, playerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Switch to the next player
     * @return nothing
     */
    @PutMapping("/game/get/{gameId}/board/switchplayer")
    public ResponseEntity<Void> switchPlayer(@PathVariable("gameId") int gameId) throws ServiceException, DaoException {
        boardService.switchCurrentPlayer(gameId);
        return new ResponseEntity<>(HttpStatus.OK);
    }




    /**
     * Endpoint for getting game information
     * @param gameId the id of the game we want to get
     * @return the game with the associated gameId we provided
     */
    @GetMapping("/game/get/{gameId}")
    public ResponseEntity<GameDto> getGame(@PathVariable("gameId") int gameId) throws ServiceException, MappingException, DaoException {
        Game game = gameService.getGame(gameId);
        return new ResponseEntity<>(dtoMapper.convertToDto(game), HttpStatus.OK);
    }

    /**
     * Endpoint for getting all games
     * @return all the games
     */
    @GetMapping("/game/list")
    public ResponseEntity<ArrayList<GameDto>> getGames() throws ServiceException, MappingException, DaoException {
        ArrayList<GameDto> gameDtos = new ArrayList<>();
        Collection<Game> games = gameService.getAllGames();

        for (Game game: games) {
            gameDtos.add(dtoMapper.convertToDto(game));
        }
        return new ResponseEntity<>(gameDtos, HttpStatus.OK);
    }

    /**
     * Remove the given game
     * @return nothing
     */
    @DeleteMapping("/game/get/{gameId}/remove")
    public ResponseEntity<Void> switchPlayer(@PathVariable("gameId") long gameId) throws ServiceException, DaoException {
        gameService.removeGame(gameId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Adds a user to a game
     * @param gameId
     * @param userId
     * @return
     * @throws ServiceException
     * @throws DaoException
     */
    @PostMapping("/game/join/{gameId}/{userId}")
    public ResponseEntity<Boolean> joinGame(@PathVariable("gameId") long gameId, @PathVariable("userId") long userId) throws ServiceException, DaoException {

     boolean result = gameService.joinGame(gameId, userId);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/game/leave/{gameId}/{userId}")
    public ResponseEntity<Void> leaveGame(@PathVariable("gameId") long gameId, @PathVariable("userId") long userId) throws ServiceException, DaoException{
        boolean result = gameService.leaveGame(gameId, userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
