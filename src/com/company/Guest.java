package com.company;

import com.company.DB.AdminRepository;
import com.company.DB.ProductRepository;
import com.company.DB.UserRepository;
import com.company.Entity.Admin;
import com.company.Entity.Order;
import com.company.Entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Guest implements LoginAsGuest {
    Scanner scanner = new Scanner(System.in);


    public void helloMenu() {
        System.out.println("Products menu-'1'");
        System.out.println("My orders menu-'2'");
        System.out.println("Корзина-'3'");
        System.out.println("Exit-'0'");
    }

    public void choiceRole() {
        while (true) {
            helloMenu();
            int chooseNumber = scanner.nextInt();
            switch (chooseNumber) {
                case 1:
                    showProducts();
                    break;
                case 2:
                    chooseProduct();
                    break;
                case 3:

                    // id замовлень. меню адміна. переглянутию підтвердити. написати
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Error, wrong answer");
                    helloMenu();
            }
        }
    }

    @Override
    public void putLoginPassword() {
        System.out.println("Login: ");
        String login = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        UserRepository userRepository = new UserRepository();
        try {
            if (userRepository.getUser(login, password)) {
                System.out.println("Успішний вхід");
                choiceRole();
            } else {
                System.out.println("Некоректно введені дані або користувача неіснує");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showProducts() {
        ProductRepository productRepository = new ProductRepository();
        List<Product> productList;
        productList = productRepository.get();
        System.out.println(productList);
    }

    public void chooseProduct() {
        ProductRepository productRepository = new ProductRepository();
        List<Product> productList;
        productList = productRepository.get();
        System.out.println("Вкажіть id товару");
        int needProduct = scanner.nextInt();
        var choosenProducts = productList.stream()
                .filter(product -> product.getId() == needProduct)
                .collect(Collectors.toList());
        System.out.println("Ви додали до вашої корзини: " + choosenProducts);
    }


    public void myCart() {


    }

    public void showAdmins() {
        AdminRepository adminRepository = new AdminRepository();
        List<Admin> adminList;
        adminList = adminRepository.get();
        System.out.println(adminList);

    }
    /// треба перевірку чи є такий юзер в базі даних, якщо нема то опція: повторити спробу або зареєструватись
}
