package com.company;

import java.util.Scanner;

public class NewGuest extends Guest implements Register {
    Scanner scanner = new Scanner(System.in);

    @Override
    public void putPersonalData() {
        System.out.println("Name");
        String firstName = scanner.nextLine();
        String secondName = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();
    }

}
