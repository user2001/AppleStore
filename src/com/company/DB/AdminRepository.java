package com.company.DB;

import com.company.Entity.AdminEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminRepository extends DBConnection implements DB {



    public List<AdminEntity> get() {
        String select = "SELECT  ID,NAME,SURNAME,LOGIN,PASSWORD FROM ADMIN";
        List<AdminEntity> admins = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
            try (PreparedStatement pst = con.prepareStatement(select)) {
                try (ResultSet resSet = pst.executeQuery()) {


                    while (resSet.next()) {
                        AdminEntity admin = new AdminEntity(
                                resSet.getInt("ID"),
                                resSet.getString("NAME"),
                                resSet.getString("SURNAME"),
                                resSet.getString("LOGIN"),
                                resSet.getString("PASSWORD"));
                        admins.add(admin);
                        System.out.println(admin);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return admins;
    }

    public AdminEntity getAdmin(String login, String password) throws SQLException {
        String select = "SELECT * FROM ADMIN WHERE LOGIN=? and PASSWORD=?";
        boolean counter = false;
        AdminEntity admin = null;
        try (Connection con = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
            try (PreparedStatement pst = con.prepareStatement(select)) {
                pst.setString(1, login);
                pst.setString(2, password);
                try (ResultSet resSet = pst.executeQuery()) {
                    if (resSet.next()) {
                        admin = new AdminEntity(resSet.getInt("ID"),
                                resSet.getString("NAME"),
                                resSet.getString("SURNAME"),
                                resSet.getString("LOGIN"),
                                resSet.getString("PASSWORD"));

                    }
                }
                return admin;
            }
        }
    }

    public boolean findAdmin(String login) throws SQLException {
        String select = "SELECT * FROM ADMIN WHERE LOGIN=?";
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
        }return counter;
    }

}
