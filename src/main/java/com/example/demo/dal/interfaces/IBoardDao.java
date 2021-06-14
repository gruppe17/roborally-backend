package com.example.demo.dal.interfaces;

import com.example.demo.exceptions.DaoException;
import com.example.demo.model.Board;


public interface IBoardDao {
	Board getBoard(int gameId) throws DaoException;

	int createBoard(Board board) throws DaoException;

	void updateBoard(Board board, int gameId) throws DaoException;

	void deleteBoard(int gameId) throws DaoException;
}
