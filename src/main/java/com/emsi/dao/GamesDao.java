package com.emsi.dao;

import com.emsi.entities.Games;
import com.emsi.entities.Games;

import java.util.List;

public interface GamesDao {

        void insert(Games games);
        void update(Games games);

        void deleteById(int id);

        Games findById(int id);

        List<Games> findAll();
}
