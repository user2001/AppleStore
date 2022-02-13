package com.company.DB.Dao;

import com.company.Entity.ProductEntity;

import java.util.List;

public interface ProductDao {

    void add(ProductEntity product);

    List<ProductEntity> get();

    List<ProductEntity> getProductsInRange(int min, int max);

    void updatePrice(int id, int price);
}
