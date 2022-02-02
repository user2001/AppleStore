package com.company.Entity;

public class Order {
    private int id;
    private int userID;
    String productsID;
    private int fullPrice;

    public Order() {
    }

    public Order(String productsID) {
        this.productsID = productsID;
    }

    public Order(int userID, String productsID, int fullPrice) {
        this.userID = userID;
        this.productsID = productsID;
        this.fullPrice = fullPrice;
    }

    public Order(int id, int userID, String productsID, int fullPrice) {
        this.id = id;
        this.userID = userID;
        this.productsID = productsID;
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

    public String getProductsID() {
        return productsID;
    }


    public void setProductsID(String productsID) {
        this.productsID = productsID;
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
                ", productsID=" + productsID +
                ", fullPrice=" + fullPrice +
                '}';
    }
}
