package com.example.demo.dal.interfaces;

import com.example.demo.exceptions.DaoException;
import com.example.demo.model.Player;


public interface IPlayerDao {

	int addPlayer(Player player) throws DaoException;
	Player getPlayer(int playerId) throws DaoException;
}
