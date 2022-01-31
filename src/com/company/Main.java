package com.company;

import com.company.DB.AdminRepository;
import com.company.DB.ProductRepository;
import com.company.DB.UserRepository;
import com.company.Entity.Product;
import com.company.Entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) throws SQLException {

        UserRepository userRepository = new UserRepository();

        User user = new User("Yaroslav","5894","Unblock"," ");
        userRepository.singUpUser(user);
        System.out.println("користувачі:");
        userRepository.select();
        ProductRepository productRepository = new ProductRepository();
        System.out.println("продукти:");
        productRepository.select();
        AdminRepository adminRepository = new AdminRepository();
        System.out.println("адміни");
        adminRepository.select();
        List<Product> productList = new ArrayList<>();
        System.out.println("список телефонів на проміжку 6000 - 27000");
        productList = productRepository.getProductsInRange(6000,27000);
        System.out.println(productList);
    }

}
//    public static void main(String[] args) throws Exception {
//        LoginMenu loginMenu = new LoginMenu();
//        loginMenu.choiceRole();
//    }

