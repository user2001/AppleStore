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
        LoginMenu loginMenu = new LoginMenu();
        loginMenu.choiceRole();







//        orderRepository.makeOrder(new Order(2, list,12000));
//        System.out.println(orderList);
    }

}
//    public static void main(String[] args) throws Exception {
//        LoginMenu loginMenu = new LoginMenu();
//        loginMenu.choiceRole();
//    }

