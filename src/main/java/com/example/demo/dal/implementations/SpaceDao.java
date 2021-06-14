package com.example.demo.dal.implementations;

import com.example.demo.dal.interfaces.ISpaceDao;
import com.example.demo.model.Space;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * Primitive implementation of a space dao, using a HashMap.
 */
@Repository
public class SpaceDao implements ISpaceDao {
    //gameId, Space[][]
    static final HashMap<Integer, Space[][]> spaces = new HashMap<>();

    @Override
    public void createSpaces(int gameId, Space[][] spacesToAdd) {
        spaces.put(gameId, spacesToAdd);
    }

    @Override
    public void updateSpaces(int gameId, Space[][] spacesToUpdate) {
        spaces.replace(gameId, spacesToUpdate);
    }

    @Override
    public Space[][] getSpaces(int gameId) {
        return spaces.get(gameId);
    }

}
