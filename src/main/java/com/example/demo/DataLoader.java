package com.example.demo;


import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.Board;
import com.example.demo.model.Player;
import com.example.demo.service.interfaces.IBoardService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * The run method is executed upon startup, this can be used to do some data seeding.
 */
@Component
public class DataLoader implements ApplicationRunner {
	private final IBoardService gameService;

	public DataLoader(IBoardService gameService) {
		this.gameService = gameService;
	}

	@Override
	public void run(ApplicationArguments args) throws ServiceException, DaoException {


	}
}
