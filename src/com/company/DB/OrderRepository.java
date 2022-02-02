package com.company.DB;

import com.company.Entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository extends DBConnection {
    public void makeOrder(Order order) throws SQLException {
        String insert = "INSERT INTO ORDERS (user_id, products_id, total_price) VALUES(?,?,?)";
        try (Connection con = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
            try (PreparedStatement pst = con.prepareStatement(insert)) {
                pst.setInt(1, order.getUserID());
                pst.setString(2, order.getProductsID());
                pst.setInt(3, order.getFullPrice());
                pst.execute();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public List<Order> get() {
        String select = "SELECT  ID, USER_ID, PRODUCTS_ID, TOTAL_PRICE FROM ORDERS";
        List<Order> orders = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
            try (PreparedStatement pst = con.prepareStatement(select)) {
                try (ResultSet resSet = pst.executeQuery()) {


                    while (resSet.next()) {
                        Order order = new Order(
                                resSet.getInt("ID"),
                                resSet.getInt("USER_ID"),
                                resSet.getString("PRODUCTS_ID"),
                                resSet.getInt("TOTAL_PRICE"));
                        orders.add(order);
                        System.out.println(order);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orders;
    }
}
