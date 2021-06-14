package com.example.demo.dal.interfaces;

import com.example.demo.exceptions.DaoException;
import com.example.demo.model.Player;
import com.example.demo.model.User;

import java.util.Collection;
import java.util.HashMap;

public interface IUserDao {
	long addUser(long gameId, User user) throws DaoException;
	long newUser() throws DaoException;
	User getUser(long userId) throws DaoException;
	HashMap<Long, User> getUsers();
}
