package com.company;

import java.util.Scanner;

public class GuestMenu {

    Scanner scanner = new Scanner(System.in);

    public void helloMenu() {
        System.out.println("Products menu-'1'");
        System.out.println("My orders menu-'2'");
        System.out.println("Admins list-'3'");
        System.out.println("Exit-'0'");
    }

    public void choiceRole()  {
        Guest user = new Guest();
        while (true) {
            helloMenu();
            int chooseNumber = scanner.nextInt();
            switch (chooseNumber) {
                case 1 :

                    break;
                case 2 :

                    break;
                case 3 :

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
