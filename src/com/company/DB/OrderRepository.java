package com.company.DB;


import com.company.Entity.OrderEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository extends DBConnection implements DBDao {
    public void makeOrder(OrderEntity order) {
        String insert = "INSERT INTO ORDERS (user_id, products_id, total_price,STATUS) VALUES(?,?,?,?)";
        try (Connection con = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
            try (PreparedStatement pst = con.prepareStatement(insert)) {
                pst.setInt(1, order.getUserID());
                pst.setString(2, order.getProductsID());
                pst.setInt(3, order.getFullPrice());
                pst.setString(4, order.getStatus());
                pst.execute();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public List<OrderEntity> get() {
        String select = "SELECT  ID, USER_ID, PRODUCTS_ID, TOTAL_PRICE, STATUS FROM ORDERS";
        List<OrderEntity> orders = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
            try (PreparedStatement pst = con.prepareStatement(select)) {
                try (ResultSet resSet = pst.executeQuery()) {
                    while (resSet.next()) {
                        OrderEntity order = new OrderEntity(
                                resSet.getInt("ID"),
                                resSet.getInt("USER_ID"),
                                resSet.getString("PRODUCTS_ID"),
                                resSet.getInt("TOTAL_PRICE"),
                                resSet.getString("STATUS"));
                        orders.add(order);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orders;
    }


    public void update(int id) {
        String update = "UPDATE ORDERS SET STATUS = ? where ID = ?";
        try (Connection con = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
            try (PreparedStatement pst = con.prepareStatement(update)) {
                pst.setString(1, "Confirmed");
                pst.setInt(2, id);
                pst.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
