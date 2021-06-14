package com.example.demo.dal.implementations;

import com.example.demo.dal.interfaces.IUserDao;
import com.example.demo.exceptions.DaoException;
import com.example.demo.model.Player;
import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

@Repository
public class UserDao implements IUserDao {
	static final HashMap<Integer, User> users = new HashMap<>();
	Random random = new Random();

	@Override
	public int addUser(int gameId, User user) {
		user.setUserId(getUniqueUserId());
		users.put(user.getUserId(), user);
		return user.getUserId();
	}

	@Override
	public int newUser() {
		User user = new User(getUniqueUserId(), "John Doe");
		users.put(user.getUserId(), user);
		return user.getUserId();
	}

	private int getUniqueUserId(){
		//Would never return if all ids are taken :(
		int id;
		while (users.containsKey(id = random.nextInt()));
		return id;
	}

	@Override
	public User getUser(int userId) {
		return users.get(userId);
	}

	@Override
	public HashMap<Integer, User> getUsers() {
		return users;
	}
}
