package com.company.Entity;

public class OrderEntity {
    private int id;
    private int userID;
    private String productsID;
    private int fullPrice;
    private String status;

    public OrderEntity(int userID, String productsID, int fullPrice, String status) {
        this.userID = userID;
        this.productsID = productsID;
        this.fullPrice = fullPrice;
        this.status = status;
    }

    public OrderEntity(int id, int userID, String productsID, int fullPrice, String status) {
        this.id = id;
        this.userID = userID;
        this.productsID = productsID;
        this.fullPrice = fullPrice;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getUserID() {
        return userID;
    }

    public String getProductsID() {
        return productsID;
    }

    public int getFullPrice() {
        return fullPrice;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", userID=" + userID +
                ", productsID='" + productsID + '\'' +
                ", fullPrice=" + fullPrice +
                ", status='" + status + '\'' +
                '}';
    }
}
