package com.company.Menu;

import com.company.DB.AdminRepository;
import com.company.DB.OrderRepository;
import com.company.DB.ProductRepository;
import com.company.DB.UserRepository;
import com.company.Entity.AdminEntity;
import com.company.Entity.OrderEntity;
import com.company.Entity.ProductEntity;
import com.company.Entity.UserEntity;
import com.company.LoginInterface;
import com.company.MenuInterface;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AdminMenu implements LoginInterface, MenuInterface {

    Scanner scanner = new Scanner(System.in);
    AdminEntity admin;

    public void helloMenu() {
        System.out.println("Users menu-'1'");  //ніби все
        System.out.println("Order menu-'2'"); //треба додати колонку confirm в таблицю
        System.out.println("Products menu-'3'"); // зробити
        System.out.println("Exit-'0'");
    }

    public void choiceRole() throws SQLException {
        while (true) {
            helloMenu();
            int chooseNumber = scanner.nextInt();
            switch (chooseNumber) {
                case 1:
                    UsersMenu();
                    break;
                case 2:
                    OrderMenu();
                    break;
                case 3:
                    ProductsMenu();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Error, wrong answer");
                    helloMenu();
            }
        }
    }

    @Override
    public void putLoginPassword() {
        System.out.println("Login: ");
        String login = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        AdminRepository adminRepository = new AdminRepository();
        try {
            if ((admin = adminRepository.getAdmin(login, password)) != null) {
                System.out.println("Успішний вхід");
                choiceRole();
            } else {
                System.out.println("Некоректно введені дані або користувача неіснує");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void UsersMenu(){
        UserRepository userRepository = new UserRepository();
        List<UserEntity> userEntityList = userRepository.get();
        System.out.println(userEntityList);
        System.out.println("Введіть id користувача та параметр: \nBlock для блокування\nUnblock  для розблокування");
        String input = scanner.nextLine();
        String[] arr = input.split(" ");
        int id = Integer.parseInt(arr[0]);
        String param = arr[1];
        if(param == "Block"){
            userRepository.update(param,id);
            System.out.println("Користувач " + id + " заблокований");
        }
        else if(param == "Unblock"){
            userRepository.update(param,id);
            System.out.println("Користувач " + id + " розблокований");
        }
        else{
            System.out.println("Некоректно введені дані");
        }
    }


    public void OrderMenu(){
        OrderRepository orderRepository = new OrderRepository();
        List<OrderEntity> orderEntityList = orderRepository.get();
        System.out.println(orderEntityList);
        System.out.println("Введіть id  для підтвердження замовлення або -1 для виходу");
        int id = scanner.nextInt();
        if(id == -1){
            return;
        }

    }

    public void ProductsMenu(){
        ProductRepository productRepository = new ProductRepository();
        System.out.println("Редагувати дані про продукт - 1 ");
        System.out.println("Додати новий продукт - 2");
        int choice = scanner.nextInt();
        List<ProductEntity> productEntities = productRepository.get();
        if(choice == 1){
            System.out.println(productEntities);
            System.out.println("Введіть id продукту");
            int id = scanner.nextInt();
            System.out.println("Виберіть що хочите змінити:\nmodel\nprice");
            String edit = scanner.next();
            if(edit == "model"){
                System.out.println("Введіть нову модель");
                String newModel = scanner.next();
                productRepository.updateModel(id,newModel);
            }
            else if(edit == "price"){
                System.out.println("Введіть нову ціну");
                int newPrice = scanner.nextInt();
                productRepository.updatePrice(id,newPrice);
            }
        }
        else if(choice == 2){
            System.out.println("Ввведіть ім'я");
            String name = scanner.next();
            System.out.println("Введіть модель");
            String model = scanner.next();
            System.out.println("Ввведіть ціну");
            int price = scanner.nextInt();
            ProductEntity product = new ProductEntity(name,model,price);
            productRepository.add(product);
            System.out.println("Продукт додано");
        }
        else {
            System.out.println("Некоректний ввід");
        }
    }
}
