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
	static final HashMap<Integer, Game> games = new HashMap<>();
	Random random = new Random();

	@Override
	public Game getGame(int gameId) {
		return games.get(gameId);
	}

	@Override
	public List<Game> getAllGames(){
		return new LinkedList<Game>(games.values());
	}

	@Override
	public int createGame(Game game) {
		game.setGameId(getUniqueGameId());
		games.put(game.getGameId(), game);
		return game.getGameId();
	}

	private int getUniqueGameId(){
		//Would never return if all ids are taken :(
		int id;
		while (games.containsKey(id = random.nextInt()) && id != -1);
		return id;
	}

	@Override
	public void updateGame(Game game, int gameId) {
		games.replace(gameId, game);
	}

	@Override
	public boolean deleteGame(int gameId) {
		return games.remove(gameId) != null;
	}
}
