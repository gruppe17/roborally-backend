package com.example.demo.dal.implementations;

import com.example.demo.dal.interfaces.IUserDao;
import com.example.demo.exceptions.DaoException;
import com.example.demo.model.Player;
import com.example.demo.model.User;

import java.util.HashMap;
import java.util.Random;

public class UserDao implements IUserDao {
    static final HashMap<Long, User> users = new HashMap<>();
    Random random = new Random();

    @Override
    public long addUser(long gameId, User user) {
        user.setUserId(getUniqueUserId());
        users.put(user.getUserId(), user);
        return user.getUserId();
    }

    private long getUniqueUserId(){
        //Would never return if all ids are taken :(
        long id;
        while (users.containsKey(id = random.nextLong()));
        return id;
    }

    @Override
    public User getUser(long userId) {
        return users.get(userId);
    }
}
