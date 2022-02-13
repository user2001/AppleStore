package com.company.DB.Dao;

import com.company.Entity.OrderEntity;

import java.util.List;

public interface OrderDao {

    void makeOrder(OrderEntity order);

    List<OrderEntity> get();

    void update(int id);
}
