package com.example.demo.util.mapping;

import com.example.demo.controller.GameController.*;
import com.example.demo.exceptions.MappingException;
import com.example.demo.model.*;

public interface IDtoMapper {
	PlayerDto convertToDto(Player player) throws MappingException;

	BoardDto convertToDto(Board board) throws MappingException;

	SpaceDto convertToDto(Space space) throws MappingException;

	GameDto convertToDto(Game game) throws MappingException;

	UserDto convertToDto(User user) throws MappingException;

	Board convertToEntity(BoardDto boardDto);

	Space convertToEntity(SpaceDto spaceDto, Board board);

	Player convertToEntity(PlayerDto playerDto, Board board) throws MappingException;

	Game convertToEntity(GameDto gameDto) throws MappingException;

	User convertToEntity(UserDto userDto, Game game) throws MappingException;
}
