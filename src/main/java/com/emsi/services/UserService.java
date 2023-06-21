package com.emsi.services;

import com.emsi.dao.impl.UserDaoImpl;
import com.emsi.entities.User;

public class UserService {
    UserDaoImpl userDao= new UserDaoImpl();

    public boolean auth(User user){
        return userDao.auth(user);
    }
}
