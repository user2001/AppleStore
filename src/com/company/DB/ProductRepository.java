package com.company.DB;

import com.company.Entity.ProductEntity;
import com.company.Entity.UserEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository extends DBConnection implements DB {


    public void add(ProductEntity product) {
        String insert = "INSERT INTO PRODUCT (NAME,MODEL,PRICE) VALUES(?,?,?)";
        try (Connection con = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
            try (PreparedStatement pst = con.prepareStatement(insert)) {
                pst.setString(1, product.getName());
                pst.setString(2, product.getModel());
                pst.setInt(3, product.getPrice());
                pst.execute();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<ProductEntity> get() {
        String select = "SELECT  ID,NAME,MODEL,PRICE FROM PRODUCT";
        List<ProductEntity> products = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
            try (PreparedStatement pst = con.prepareStatement(select)) {
                try (ResultSet resSet = pst.executeQuery()) {
                    while (resSet.next()) {
                        ProductEntity product = new ProductEntity(
                                resSet.getInt("ID"),
                                resSet.getString("NAME"),
                                resSet.getString("MODEL"),
                                resSet.getInt("PRICE"));
                        products.add(product);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }


    public List<ProductEntity> getProductsInRange(int min, int max)throws SQLException {
        String select = "SELECT * FROM PRODUCT where PRICE between ? and ?";
        try(Connection con = DriverManager.getConnection(URL, LOGIN, PASSWORD)){
            try(PreparedStatement pst = con.prepareStatement(select)){
                pst.setInt(1,min);
                pst.setInt(2,max);
                try(ResultSet resSet = pst.executeQuery()){
                    List<ProductEntity> products = new ArrayList<>();
                    while (resSet.next()){
                        ProductEntity product = new ProductEntity(
                                resSet.getInt("ID"),
                                resSet.getString("NAME"),
                                resSet.getString("MODEL"),
                                resSet.getInt("PRICE"));
                        products.add(product);
                    }
                    return products;
                }
            }
        }
    }

    public void updatePrice(int id, int price) {
        String update = "UPDATE PRODUCT SET PRICE = ? where ID = ?";
        List<UserEntity> users = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
            try (PreparedStatement pst = con.prepareStatement(update)) {
                pst.setInt(1,price);
                pst.setInt(2, id);
                pst.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateModel(int id,String model) {
        String update = "UPDATE PRODUCT SET MODEL = ? where ID = ?";
        List<UserEntity> users = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
            try (PreparedStatement pst = con.prepareStatement(update)) {
                pst.setString(1,model);
                pst.setInt(2, id);
                pst.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete() {

    }
}
