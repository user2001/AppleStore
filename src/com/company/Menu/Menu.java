package com.company.Menu;

import java.sql.SQLException;

public interface Menu {
    void helloMenu();

    void choiceRole() throws SQLException;

    default void showItems(String[] items) {
        System.out.println("-------------");
        for (String item : items) {
            System.out.println(item);
        }
        System.out.println("-------------");
    }
}
