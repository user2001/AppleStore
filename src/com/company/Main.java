package com.company;

import com.company.Menu.LoginMenu;

public class Main {

    //при запуску поміняйти шлях в DBConnection а свій щоб не було помилок
    public static void main(String[] args) {
        LoginMenu loginMenu = new LoginMenu();
        loginMenu.choiceRole();
    }

}


