package com.example.demo.service.interfaces;

import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface IUserService {

	long createUser() throws ServiceException, DaoException;

	HashMap<Long, User> getUsers() throws ServiceException, DaoException;

	User getUser(long id) throws ServiceException, DaoException;

	User changeUserName(long id, String userName) throws ServiceException, DaoException;


}
