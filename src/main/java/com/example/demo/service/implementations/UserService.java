package com.example.demo.service.implementations;

import com.example.demo.dal.implementations.UserDao;
import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.User;
import com.example.demo.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserService implements IUserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public long createUser() throws ServiceException, DaoException {
        return userDao.newUser();
    }

    @Override
    public HashMap<Long, User> getUsers() throws ServiceException, DaoException {
        return userDao.getUsers();
    }

    @Override
    public User changeUserName(long id, String userName) throws ServiceException, DaoException {
        userDao.getUser(id).setUserName(userName);
        return userDao.getUser(id);
    }

    @Override
    public User getUser(long id) throws ServiceException, DaoException {
        return userDao.getUser(id);
    }
}
