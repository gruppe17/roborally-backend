package com.example.demo.controller.GameController;

import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.MappingException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.*;
import com.example.demo.service.implementations.UserService;
import com.example.demo.service.interfaces.IBoardService;
import com.example.demo.service.interfaces.IGameService;
import com.example.demo.service.interfaces.IUserService;
import com.example.demo.util.mapping.IDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@RestController
@CrossOrigin
public class GameController {
	private final IBoardService boardService;
	private final IGameService gameService;
	private final IUserService userService;
	private final IDtoMapper dtoMapper;

	public GameController(IBoardService boardService, IGameService gameService, IUserService userService, IDtoMapper dtoMapper) {
		this.boardService = boardService;
		this.gameService = gameService;
		this.userService = userService;
		this.dtoMapper = dtoMapper;
	}


	/**
	 * Endpoint for getting game information
	 * @param gameId the id of the game we want to get
	 * @return the game with the associated gameId we provided
	 */
	@GetMapping("/game/get/{gameId}")
	public ResponseEntity<GameDto> getGame(@PathVariable("gameId") int gameId) throws ServiceException, MappingException, DaoException {
		Game game = gameService.getGame(gameId);
		System.out.println(game.getGameId());

		return new ResponseEntity<>(dtoMapper.convertToDto(game), HttpStatus.OK);
	}

	/**
	 * Endpoint for getting board information
	 * @param gameId the id of the board we want to get
	 * @return the board with the associated gameId we provided
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
	@PostMapping("/game/get/{gameId}/board/player")
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
		long gameId = gameService.createGame();
		return new ResponseEntity<>(gameId, HttpStatus.CREATED);
	}

	/**
	 * Removes current board and creates a new one for the given game
	 * @param gameId
	 * @return
	 * @throws ServiceException
	 * @throws DaoException
	 */
	@PostMapping("/game/get/{gameId}/board/new")
	public ResponseEntity<Integer> createBoard(@PathVariable("gameId") int gameId) throws ServiceException, DaoException {
		Game game = gameService.getGame(gameId);
		boardService.removeBoard((int)gameId);
		Board board = new Board(8, 8, "Board");
		board.setGameId(gameId);
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
	 * Endpoint for getting all games
	 * @return all the games
	 */
	@GetMapping("/game/all")
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
	public ResponseEntity<Boolean> removeGame(@PathVariable("gameId") int gameId) throws ServiceException, DaoException {
		boolean result = gameService.removeGame(gameId);
		return new ResponseEntity<>(result, HttpStatus.OK);
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
	public ResponseEntity<Boolean> joinGame(@PathVariable("gameId") int gameId, @PathVariable("userId") int userId) throws ServiceException, DaoException {
		boolean result = gameService.joinGame(gameId, userId);
		User user = userService.getUser(userId);
		Board board = boardService.getBoard(gameId);
		if(board == null || user == null){
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}
		Player player = new Player(board, "red", user.getUserName());
		player.setPlayerId(userId);
		boardService.addPlayer(gameId, player);

		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	/**
	 * Removes a user from a game
	 * @param gameId
	 * @param userId
	 * @return
	 * @throws ServiceException
	 * @throws DaoException
	 */
	@PutMapping("/game/leave/{gameId}/{userId}")
	public ResponseEntity<Boolean> leaveGame(@PathVariable("gameId") int gameId, @PathVariable("userId") int userId) throws ServiceException, DaoException{
		boolean result = gameService.leaveGame(gameId, userId);
		Board board = boardService.getBoard(gameId);

		if(board == null ){
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}

		Player player = board.getPlayer(userId);

		if(player == null ){
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}

		board.removePlayer(player);

		return new ResponseEntity<>(board.getPlayer(userId) == null, HttpStatus.OK);
	}

	/**
	 * Changes the name of the given game
	 * @param gameId
	 * @param name
	 * @return
	 * @throws ServiceException
	 * @throws DaoException
	 */
	@PutMapping("/game/get/{gameId}/edit/{name}")
	public ResponseEntity<Boolean> editGame(@PathVariable("gameId") int gameId, @PathVariable("name") String name) throws ServiceException, DaoException{
		boolean result = gameService.editGameName(gameId, name);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}


	/**
	 * Creates a new user
	 * @return
	 * @throws ServiceException
	 * @throws DaoException
	 */
	@PostMapping("/user/new")
	public ResponseEntity<Integer> newUser() throws ServiceException, DaoException {
		int id = userService.createUser();
		return new ResponseEntity<>(id, HttpStatus.CREATED);
	}

	/**
	 * Changes the name of a user
	 * @param userId
	 * @param name
	 * @return
	 * @throws ServiceException
	 * @throws DaoException
	 */
	@PutMapping("/user/get/{userId}/edit/{name}")
	public ResponseEntity<Void> editUserName(@PathVariable("userId") int userId, @PathVariable("name") String name) throws ServiceException, DaoException{
		User result = userService.changeUserName(userId, name);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Get a user with the given userId
	 * @param userId
	 * @return
	 * @throws ServiceException
	 * @throws MappingException
	 * @throws DaoException
	 */
	@GetMapping("/user/get/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable("userId") int userId) throws ServiceException, MappingException, DaoException {
		User user = userService.getUser(userId);
		return new ResponseEntity<>(dtoMapper.convertToDto(user), HttpStatus.OK);
	}

	/**
	 * Gets all the users
	 * @return
	 * @throws ServiceException
	 * @throws MappingException
	 * @throws DaoException
	 */
	@GetMapping("/user/all")
	public ResponseEntity<ArrayList<UserDto>> getUsers() throws ServiceException, MappingException, DaoException {
		ArrayList<UserDto> userDtos = new ArrayList<>();
		HashMap<Integer, User> users = userService.getUsers();

		for (User user: users.values()) {
			userDtos.add(dtoMapper.convertToDto(user));
		}
		return new ResponseEntity<>(userDtos, HttpStatus.OK);
	}


	@DeleteMapping("/user/get/{userId}/remove")
	public ResponseEntity<Boolean> deleteUser(@PathVariable("userId") int userId) throws ServiceException, DaoException{
		boolean result = userService.removeUser(userId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
