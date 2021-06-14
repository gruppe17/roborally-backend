package com.example.demo.dal.interfaces;

import com.example.demo.exceptions.DaoException;
import com.example.demo.model.Player;
import com.example.demo.model.User;

import java.util.Collection;
import java.util.HashMap;

public interface IUserDao {
	int addUser(int gameId, User user) throws DaoException;
	int newUser() throws DaoException;
	User getUser(int userId) throws DaoException;
	HashMap<Integer, User> getUsers();
}
