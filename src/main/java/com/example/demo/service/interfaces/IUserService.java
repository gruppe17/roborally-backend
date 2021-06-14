package com.example.demo.service.interfaces;

import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface IUserService {

	int createUser() throws ServiceException, DaoException;

	HashMap<Integer, User> getUsers() throws ServiceException, DaoException;

	User getUser(int id) throws ServiceException, DaoException;

	User changeUserName(int id, String userName) throws ServiceException, DaoException;


}
