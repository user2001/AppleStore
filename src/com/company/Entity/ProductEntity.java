package com.company.Entity;

public class ProductEntity {

    private int id;
    private String name;
    private String model;
    private int price;


    public ProductEntity(String name, String model, int price) {
        this.name = name;
        this.model = model;
        this.price = price;
    }

    public ProductEntity(int id, String name, String model, int price) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.price = price;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getModel() {
        return model;
    }


    public int getPrice() {
        return price;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                '}';
    }

}
