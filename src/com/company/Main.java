package com.company;

import com.company.DB.AdminRepository;
import com.company.DB.OrderRepository;
import com.company.DB.ProductRepository;
import com.company.DB.UserRepository;
import com.company.Entity.Order;
import com.company.Entity.Product;
import com.company.Entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {


    public static void main(String[] args) throws Exception {
//        LoginMenu loginMenu = new LoginMenu();
//        loginMenu.choiceRole();
        UserRepository userRepository = new UserRepository();






        System.out.println("користувачі:");
        userRepository.select();
        ProductRepository productRepository = new ProductRepository();
        System.out.println("продукти:");
        productRepository.add(new Product("Iphone","6s",11000));
        productRepository.get();
        AdminRepository adminRepository = new AdminRepository();
        System.out.println("адміни");
        adminRepository.get();
        List<Product> productList;
        System.out.println("список телефонів на проміжку 6000 - 27000");
        productList = productRepository.getProductsInRange(6000,27000);
        System.out.println(productList);
        OrderRepository orderRepository = new OrderRepository();
        List<Order> orderList;
        orderList = orderRepository.get();
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list,1,2,3);
//        orderRepository.makeOrder(new Order(2, list,12000));
//        System.out.println(orderList);
    }

}
//    public static void main(String[] args) throws Exception {
//        LoginMenu loginMenu = new LoginMenu();
//        loginMenu.choiceRole();
//    }

