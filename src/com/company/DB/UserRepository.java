package com.company.DB;

import com.company.Entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends DBConnection {

    public void singUpUser(User user) throws SQLException {
        if(findUser(user.getLogin())){
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

    public boolean findUser(String login) throws SQLException {
        String select = "SELECT COUNT(*) FROM USER WHERE LOGIN=?";
        boolean counter = false;
        try (Connection con = DriverManager.getConnection(URL, LOGIN, PASSWORD);) {
            try (PreparedStatement pst = con.prepareStatement(select);) {
                pst.setString(1, login);
                try (ResultSet resSet = pst.executeQuery()) {
                    if (resSet.next()) {
                        counter = true;

                    }
                }

            }
        }return counter;
    }


    public List<User> select() {
        String select = "SELECT  ID,LOGIN,PASSWORD,STATUS,MESSAGE FROM USER";
        List<User> users = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
            try (PreparedStatement pst = con.prepareStatement(select)) {
                try (ResultSet resSet = pst.executeQuery()) {


                    while (resSet.next()) {
                        User user = new User(
                                resSet.getInt("ID"),
                                resSet.getString("LOGIN"),
                                resSet.getString("PASSWORD"),
                                resSet.getString("STATUS"),
                                resSet.getString("MESSAGE"));
                        users.add(user);
                        System.out.println(user);
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
        List<User> users = new ArrayList<>();
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

    public void delete(int id) {
        String delete = "DELETE FROM USER WHERE id =? ";
        try (Connection con = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
            try (PreparedStatement pst = con.prepareStatement(delete)) {
                pst.setInt(1, id);
                pst.execute();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
