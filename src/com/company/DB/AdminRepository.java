package com.company.DB;

import com.company.Entity.Admin;

import java.sql.*;

public class AdminRepository extends DBConnection {


    public void insert() {

    }

    public void select() {
        String select = "SELECT  ID,NAME,SURNAME,LOGIN,PASSWORD FROM ADMIN";
        try (Connection con = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
            try (PreparedStatement pst = con.prepareStatement(select)) {
                try (ResultSet resSet = pst.executeQuery()) {


                    while (resSet.next()) {
                        Admin admin = new Admin(
                                resSet.getInt("ID"),
                                resSet.getString("NAME"),
                                resSet.getString("SURNAME"),
                                resSet.getString("LOGIN"),
                                resSet.getString("PASSWORD"));
                        //users.add(admin);
                        System.out.println(admin);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void update() {

    }

    public void delete() {

    }
}
