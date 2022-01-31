package com.company.Entity;

import java.util.Arrays;

public class Order {
    private int id;
    private int userID;
    private int[] productsId;
    private int fullPrice;

    public Order() {
    }

    public Order(int userID, int[] productsId, int fullPrice) {
        this.userID = userID;
        this.productsId = productsId;
        this.fullPrice = fullPrice;
    }

    public Order(int id, int userID, int[] productsId, int fullPrice) {
        this.id = id;
        this.userID = userID;
        this.productsId = productsId;
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

    public int[] getProductsId() {
        return productsId;
    }

    public void setProductsId(int[] productsId) {
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
                ", productsId=" + Arrays.toString(productsId) +
                ", fullPrice=" + fullPrice +
                '}';
    }
}
