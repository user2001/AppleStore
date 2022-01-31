package com.company.DB;

import com.company.Entity.Admin;
import com.company.Entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminRepository extends DBConnection {


    public void insert() {

    }

    public List<Admin> get() {
        String select = "SELECT  ID,NAME,SURNAME,LOGIN,PASSWORD FROM ADMIN";
        List<Admin> admins = new ArrayList<>();
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


    public void update() {

    }

    public void delete() {

    }
}
