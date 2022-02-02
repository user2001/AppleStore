package com.company.Entity;

import java.sql.Array;
import java.sql.SQLException;
import java.util.List;

public class Order {
    private int id;
    private int userID;
    private List<Product> productsId;
    private int fullPrice;

    public Order() {
    }

    public Order(List<Product> productsId) {
        this.productsId = productsId;
    }

    public Order(int userID, List<Product> productsId, int fullPrice) {
        this.userID = userID;
        this.productsId = productsId;
        this.fullPrice = fullPrice;
    }

    public Order(int id, int userID, Array productsId, int fullPrice) throws SQLException {
        this.id = id;
        this.userID = userID;
        this.productsId = (List<Product>) productsId.getResultSet();
        this.fullPrice = fullPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public List<Product> getProductsId() {
        return productsId;
    }

    public void setProductsId(List<Product> productsId) {
        this.productsId = productsId;
    }

    public int getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(int fullPrice) {
        this.fullPrice = fullPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userID=" + userID +
                ", productsId=" + productsId +
                ", fullPrice=" + fullPrice +
                '}';
    }
}
