package com.company.Menu;

import java.util.Scanner;

public class LoginMenu implements Menu {
    Scanner scanner = new Scanner(System.in);

    private String[] items = {"Guest-'1'", "NewGuest-'2'","Admin-'3'","Exit-'0'"};


    public void helloMenu() {
       showItems(items);
    }

    public void choiceRole(){

        while (true) {
            helloMenu();
            int chooseNumber = scanner.nextInt();
            switch (chooseNumber) {
                case 1:
                    new GuestMenu().putLoginPassword();
                    break;
                case 2:
                    new NewGuestMenu().putPersonalData();
                    break;
                case 3:
                    new AdminMenu().putLoginPassword();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Error, wrong answer");
                    helloMenu();
            }
        }
    }

}

