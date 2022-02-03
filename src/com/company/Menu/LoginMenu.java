package com.company.Menu;

import java.util.Scanner;

public class LoginMenu {
    Scanner scanner = new Scanner(System.in);

    public void helloMenu() {
        System.out.println("Guest-'1'");
        System.out.println("NewGuest-'2'");
        System.out.println("Admin-'3'");
        System.out.println("Exit-'0'");
    }

    public void choiceRole(){

        while (true) {
            helloMenu();
            int chooseNumber = scanner.nextInt();
            switch (chooseNumber) {
                case 1 :
                    GuestMenu user=new GuestMenu();
                    user.putLoginPassword();
//                   user.confirmOrder();
                    break;
                case 2 :
                    NewGuestMenu newUser =new NewGuestMenu();
                    newUser.putPersonalData();
//                    newUser.chooseProducts();
//                    newUser.confirmOrder();
                    break;
// register

                case 3 :
                    // admin
                    break;
                case 0 :
                    System.exit(0);

                default :
                    System.out.println("Error, wrong answer");
                    helloMenu();

            }
        }
    }

}

