package com.emsi.dao;

import com.emsi.entities.User;

public interface UserDao {
    boolean auth(User user);
}
