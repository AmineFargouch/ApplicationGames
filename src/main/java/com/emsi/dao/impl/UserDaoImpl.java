package com.emsi.dao.impl;

import com.emsi.dao.UserDao;
import com.emsi.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao{
    private Connection conn= DB.getConnection();
    @Override
    public boolean auth(User user) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = conn.prepareStatement("SELECT * FROM `user` WHERE email=?");
            ps.setString(1, user.getEmail());
            rs = ps.executeQuery();



            if (rs.next()) {
                if(user.getPassword().equals(rs.getString("password"))){
                    return true;
                }else {
                    return false;
                }
            }

        } catch (SQLException e) {
            System.err.println("problème de requête pour se connecter");;
            return false;
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }
        return false;
    }
}
