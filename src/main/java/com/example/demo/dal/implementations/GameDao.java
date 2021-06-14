package com.example.demo.dal.implementations;

import com.example.demo.dal.interfaces.IGameDao;
import com.example.demo.model.Game;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Primitive implementation of a game dao, using a HashMap.
 */
@Repository
public class GameDao implements IGameDao {
	//GameId, Game
	static final HashMap<Long, Game> games = new HashMap<>();
	Random random = new Random();

	@Override
	public Game getGame(long gameId) {
		return games.get(gameId);
	}

	@Override
	public List<Game> getAllGames(){
		return new LinkedList<Game>(games.values());
	}

	@Override
	public long createGame(Game game) {
		game.setGameId(getUniqueGameId());
		games.put(game.getGameId(), game);
		return game.getGameId();
	}

	private long getUniqueGameId(){
		//Would never return if all ids are taken :(
		long id;
		while (games.containsKey(id = random.nextLong()));
		return id;
	}

	@Override
	public void updateGame(Game game, long gameId) {
		games.replace(gameId, game);
	}

	@Override
	public boolean deleteGame(long gameId) {
		return games.remove(gameId) != null;
	}
}
