package com.example.demo.service.implementations;

import com.example.demo.dal.implementations.UserDao;
import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.User;
import com.example.demo.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService implements IUserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public User createUser() throws ServiceException, DaoException {
        return null;
    }

    @Override
    public Collection<User> getUsers() throws ServiceException, DaoException {
        return null;
    }

    @Override
    public User changeUserName(String userName) throws ServiceException, DaoException {
        return null;
    }
}
