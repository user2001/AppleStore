package com.company.Menu;

import com.company.DB.UserRepository;
import com.company.Entity.UserEntity;
import com.company.Menu.GuestMenu;
import com.company.Register;

import java.sql.SQLException;
import java.util.Scanner;

public class NewGuestMenu extends GuestMenu implements Register {
    Scanner scanner = new Scanner(System.in);

    @Override
    public void putPersonalData() {
        System.out.println("Login");
        String login = scanner.next();
        System.out.println("Password");
        String password = scanner.next();
        UserEntity user = new UserEntity(login,password,"Unblock","");
        UserRepository userRepository = new UserRepository();
        try {
            userRepository.singUpUser(user);
            GuestMenu guest = new GuestMenu();
            guest.putLoginPassword();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void putLoginPassword() {

    }
}
