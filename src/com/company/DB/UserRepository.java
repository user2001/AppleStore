package com.company.DB;


import com.company.DB.Dao.UserDao;
import com.company.Entity.UserEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends DBConnection implements UserDao {

    public void singUpUser(UserEntity user){
        if (findUser(user.getLogin())) {
            System.out.println("Такий користувач вже існує");
            return;
        }
        String insert = "INSERT INTO USER (LOGIN, PASSWORD, STATUS, MESSAGE) VALUES(?,?,?,?)";
        try (Connection con = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
            try (PreparedStatement pst = con.prepareStatement(insert)) {
                pst.setString(1, user.getLogin());
                pst.setString(2, user.getPassword());
                pst.setString(3, user.getStatus());
                pst.setString(4, user.getMessage());
                pst.execute();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public UserEntity getUser(String login, String password){
        String select = "SELECT * FROM User WHERE LOGIN=? and PASSWORD=?";
        boolean counter = false;
        UserEntity user = null;
        try (Connection con = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
            try (PreparedStatement pst = con.prepareStatement(select)) {
                pst.setString(1, login);
                pst.setString(2, password);
                try (ResultSet resSet = pst.executeQuery()) {
                    if (resSet.next()) {
                        user = new UserEntity(resSet.getInt("ID"),
                                resSet.getString("LOGIN"),
                                resSet.getString("PASSWORD"),
                                resSet.getString("STATUS"),
                                resSet.getString("MESSAGE"));
                    }
                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public boolean findUser(String login){
        String select = "SELECT * FROM USER WHERE LOGIN=?";
        boolean counter = false;
        try (Connection con = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
            try (PreparedStatement pst = con.prepareStatement(select)) {
                pst.setString(1, login);
                try (ResultSet resSet = pst.executeQuery()) {
                    if (resSet.next()) {
                        counter = true;
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return counter;
    }


    public List<UserEntity> get() {
        String select = "SELECT  ID,LOGIN,PASSWORD,STATUS,MESSAGE FROM USER";
        List<UserEntity> users = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
            try (PreparedStatement pst = con.prepareStatement(select)) {
                try (ResultSet resSet = pst.executeQuery()) {


                    while (resSet.next()) {
                        UserEntity user = new UserEntity(
                                resSet.getInt("ID"),
                                resSet.getString("LOGIN"),
                                resSet.getString("PASSWORD"),
                                resSet.getString("STATUS"),
                                resSet.getString("MESSAGE"));
                        users.add(user);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public void update(String status,int id) {
        String update = "UPDATE USER SET STATUS = ? where ID = ?";
        List<UserEntity> users = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
            try (PreparedStatement pst = con.prepareStatement(update)) {
                    pst.setString(1,status);
                    pst.setInt(2, id);
                    pst.execute();

                }
            } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
