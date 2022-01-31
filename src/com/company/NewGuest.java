package com.company;

import com.company.DB.UserRepository;
import com.company.Entity.User;

import java.sql.SQLException;
import java.util.Scanner;

public class NewGuest extends Guest implements Register {
    Scanner scanner = new Scanner(System.in);

    @Override
    public void putPersonalData() {
        System.out.println("Name");
        String firstName = scanner.nextLine();
        System.out.println("Surname");
        String secondName = scanner.nextLine();
        System.out.println("Login");
        String login = scanner.nextLine();
        System.out.println("Password");
        String password = scanner.nextLine();
        User user = new User(firstName,secondName,login,password);
        UserRepository userRepository = new UserRepository();
        try {
            userRepository.singUpUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void putLoginPassword() {

    }
}
