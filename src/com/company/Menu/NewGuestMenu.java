package com.company.Menu;

import com.company.Dao.DBDao.UserRepository;
import com.company.Entity.UserEntity;
import com.company.RegisterInterface;

import java.sql.SQLException;
import java.util.Scanner;

public class NewGuestMenu  implements RegisterInterface{
    Scanner scanner = new Scanner(System.in);

    @Override
    public void putPersonalData() {
        System.out.println("Login");
        String login = scanner.next();
        System.out.println("Password");
        String password = scanner.next();
        UserEntity user = new UserEntity(login, password, "Unblock", "");
        UserRepository userRepository = new UserRepository();
        try {
            userRepository.singUpUser(user);
            new GuestMenu().putLoginPassword();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
