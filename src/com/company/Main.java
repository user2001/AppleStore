package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static  final String url = "jdbc:h2:D:\\IDEAProjects\\DBTest\\db";
    private static  final String login = "Nikos";
    private static  final String password = "123455";


    private static Connection con;
    private static Statement stm;
    private static PreparedStatement pstm;
    private static ResultSet rs;

    public static void main(String[] args) throws SQLException{

        String select = "SELECT ID,LOGIN,PASSWORD,STATUS FROM Users";
        List<User> users = new ArrayList<>();
//        Class.forName("org.h2.Driver");
//        Connection con = DriverManager.getConnection(url);
        try(Connection con = DriverManager.getConnection(url,login,password)) {
            try (PreparedStatement pst = con.prepareStatement(select)) {
                try (ResultSet resSet = pst.executeQuery()) {


                    while (resSet.next()) {
                        User user = new User(
                                resSet.getInt("ID"),
                                resSet.getString("LOGIN"),
                                resSet.getString("PASSWORD"),
                                resSet.getString("STATUS"));
                        //users.add(user);
                        System.out.println(user);
                    }
                }
            }
        }
        //System.out.println(users);
    }
//    public static void main(String[] args) throws Exception {
//        LoginMenu loginMenu = new LoginMenu();
//        loginMenu.choiceRole();
//    }
}
