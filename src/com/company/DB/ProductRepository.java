package com.company.DB;

import com.company.Entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository extends DBConnection {


    public void add(Product product) {
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

    public List<Product> select() {
        String select = "SELECT  ID,NAME,MODEL,PRICE FROM PRODUCT";
        List<Product> products = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
            try (PreparedStatement pst = con.prepareStatement(select)) {
                try (ResultSet resSet = pst.executeQuery()) {


                    while (resSet.next()) {
                        Product product = new Product(
                                resSet.getInt("ID"),
                                resSet.getString("NAME"),
                                resSet.getString("MODEL"),
                                resSet.getInt("PRICE"));
                        products.add(product);
                        System.out.println(product);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }


    public List<Product> getProductsInRange(int min,int max)throws SQLException {
        String select = "SELECT * FROM PRODUCT where PRICE between ? and ?";
        try(Connection con = DriverManager.getConnection(URL, LOGIN, PASSWORD)){
            try(PreparedStatement pst = con.prepareStatement(select)){
                pst.setInt(1,min);
                pst.setInt(2,max);
                try(ResultSet resSet = pst.executeQuery()){
                    List<Product> products = new ArrayList<>();
                    while (resSet.next()){
                        Product product = new Product(
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

    public void update() {

    }

    public void delete() {

    }
}
