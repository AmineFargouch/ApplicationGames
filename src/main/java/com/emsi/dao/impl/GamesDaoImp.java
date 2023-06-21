package com.emsi.dao.impl;

import com.emsi.dao.GamesDao;
import com.emsi.entities.Games;
import com.emsi.entities.Games;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class GamesDaoImp implements GamesDao {

    private Connection conn = DB.getConnection();

    @Override
    public void insert(Games games) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("INSERT INTO games (Name, Plateforme, Prix , Note, Type, Developpeur) " +
                    "VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, games.getName());
            ps.setString(2, games.getPlateforme());
            ps.setDouble(3, games.getPrix());
            ps.setInt(4, games.getNote());
            ps.setString(5, games.getType());
            ps.setString(6, games.getDeveloppeur());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    int id = rs.getInt(1);
                    games.setId(id);
                }

                DB.closeResultSet(rs);
            } else {
                System.out.println("No rows affected");
            }
        } catch (SQLException e) {
            System.err.println("Failed to insert an games");
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void update(Games games) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("UPDATE games SET Name = ?, Plateforme = ?, Prix = ?, Note = ?, " +
                    "Type = ?, Developpeur = ? WHERE Id = ?");

            ps.setString(1, games.getName());
            ps.setString(2, games.getPlateforme());
            ps.setDouble(3, games.getPrix());
            ps.setInt(4, games.getNote());
            ps.setString(5, games.getType());
            ps.setString(6, games.getDeveloppeur());
            ps.setInt(7, games.getId());


            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to update an Games");
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void deleteById(int id) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("DELETE FROM games WHERE id = ?");

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to delete an games");
        } finally {
            DB.closeStatement(ps);
        }
    }


    @Override
    public Games findById(int id) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM games WHERE id = ?");

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                Games games = new Games();

                games.setId(rs.getInt("Id"));
                games.setName(rs.getString("Name"));
                games.setPlateforme(rs.getString("Plateforme"));
                games.setPrix(rs.getDouble("Prix"));
                games.setNote(rs.getInt("Note"));
                games.setType(rs.getString("Type"));
                games.setDeveloppeur(rs.getString("Developpeur"));

                return games;
            }

            return null;
        } catch (SQLException e) {
            System.err.println("problème de requête pour trouver un département");
            return null;
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }
    }

    @Override
    public List<Games> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM games");
            rs = ps.executeQuery();

            List<Games> listGames = new ArrayList<>();

            while (rs.next()) {
                Games games = new Games();

                games.setId(rs.getInt("Id"));
                games.setName(rs.getString("Name"));
                games.setPlateforme(rs.getString("Plateforme"));
                games.setPrix(rs.getDouble("Prix"));
                games.setNote(rs.getInt("Note"));
                games.setType(rs.getString("Type"));
                games.setDeveloppeur(rs.getString("Developpeur"));

                listGames.add(games);
            }

            return listGames;
        } catch (SQLException e) {
            System.err.println("problème de requête pour sélectionner un game");;
            return null;
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }

    }

}

