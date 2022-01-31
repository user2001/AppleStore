package com.company;

import com.company.DB.AdminRepository;
import com.company.DB.ProductRepository;
import com.company.DB.UserRepository;
import com.company.Entity.Admin;
import com.company.Entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Guest implements LoginAsGuest {

    Scanner scanner = new Scanner(System.in);


    public void helloMenu() {
        System.out.println("Products menu-'1'");
        System.out.println("My orders menu-'2'");
        System.out.println("Admins list-'3'");
        System.out.println("Exit-'0'");
    }


    public void choiceRole(){
        Guest user = new Guest();
        while (true) {
            helloMenu();
            int chooseNumber = scanner.nextInt();
            switch (chooseNumber) {
                case 1 :
                        chooseProducts();
                    break;
                case 2 :

                    break;
                case 3 :
                    showAdmins();
                    break;
                case 0 :
                    System.exit(0);
                default :
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

    public void chooseProducts() {
        ProductRepository productRepository = new ProductRepository();
        List<Product> productList;
        productList = productRepository.get();
        System.out.println(productList);
    }

    public void confirmOrder() {

    }

    public void showAdmins(){
        AdminRepository adminRepository = new AdminRepository();
        List<Admin> adminList;
        adminList = adminRepository.get();
        System.out.println(adminList);

    }
    /// треба перевірку чи є такий юзер в базі даних, якщо нема то опція: повторити спробу або зареєструватись
}
