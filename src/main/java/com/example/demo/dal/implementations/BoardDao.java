package com.example.demo.dal.implementations;

import com.example.demo.dal.interfaces.IBoardDao;
import com.example.demo.model.Board;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * Primitive implementation of a board dao, using a HashMap.
 */
@Repository
public class BoardDao implements IBoardDao {
	//gameId, Board
	static final HashMap<Integer, Board> boards = new HashMap<>();

	@Override
	public Board getBoard(int gameId) {
		return boards.get(gameId);
	}

	@Override
	public int createBoard(Board board) {
		boards.put(board.getGameId(), board);
		return board.getGameId();
	}

	@Override
	public void updateBoard(Board board, int gameId) {
		boards.replace(gameId, board);
	}

	@Override
	public void deleteBoard(int gameId) {
		boards.remove(gameId);
	}
}
