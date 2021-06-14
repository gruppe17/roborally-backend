package com.example.demo.dal.interfaces;

import com.example.demo.exceptions.DaoException;
import com.example.demo.model.Player;
import com.example.demo.model.User;

public interface IUserDao {
    long addUser(long gameId, User user) throws DaoException;
    User getUser(long userId) throws DaoException;
}
