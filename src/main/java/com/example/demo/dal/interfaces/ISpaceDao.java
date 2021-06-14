package com.example.demo.dal.interfaces;

import com.example.demo.exceptions.DaoException;
import com.example.demo.model.Space;


public interface ISpaceDao {
	void createSpaces(int gameId, Space[][] spaces) throws DaoException;

	void updateSpaces(int gameId, Space[][] spaces) throws DaoException;

	Space[][] getSpaces(int gameId) throws DaoException;
}
