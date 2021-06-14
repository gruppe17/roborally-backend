package com.example.demo.service.interfaces;

import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface IUserService {

    User createUser() throws ServiceException, DaoException;

    Collection<User> getUsers() throws ServiceException, DaoException;

    User changeUserName(String userName) throws ServiceException, DaoException;


}
