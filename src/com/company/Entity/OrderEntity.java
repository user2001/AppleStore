package com.company.Entity;

public class OrderEntity {
    private int id;
    private int userID;
    private String productsID;
    private int fullPrice;
    private String status;

    public OrderEntity() {
    }

    public OrderEntity(String productsID) {
        this.productsID = productsID;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
