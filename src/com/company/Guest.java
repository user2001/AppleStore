package com.company;

import java.util.Scanner;

public class Guest implements LoginAsGuest {

    Scanner scanner = new Scanner(System.in);

    @Override
    public void putLoginPassword() {
        System.out.println("Login: ");
        String login = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();

    }

    public void chooseProducts() {

    }

    public void confirmOrder() {

    }
    /// треба перевірку чи є такий юзер в базі даних, якщо нема то опція: повторити спробу або зареєструватись
}
