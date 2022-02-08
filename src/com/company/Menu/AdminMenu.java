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

    public void choiceRole() {
        while (true) {
            helloMenu();
            int chooseNumber = scanner.nextInt();
            scanner.nextLine();
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


    public void UsersMenu() {
        UserRepository userRepository = new UserRepository();
        List<UserEntity> userEntityList = userRepository.get();
        System.out.println(userEntityList);
        for (UserEntity u:userEntityList) {
            System.out.println(u);
        }
        System.out.println("Введіть id користувача та параметр: \nBlock для блокування\nUnblock  для розблокування");
        String input = scanner.nextLine();
        String[] arr = input.split(" ");
        int id = Integer.parseInt(arr[0]);
        String param = arr[1];
        var user = userEntityList.stream().filter(it -> it.getId() == id).findFirst().get();
        if (param.equals("Block")) {
            userRepository.update(param, id);
            System.out.println("Користувач " + user.getLogin() + " заблокований");
        } else if (param.equals("Unblock")) {
            userRepository.update(param, id);
            System.out.println("Користувач " + user.getLogin() + " розблокований");
        } else {
            System.out.println("Некоректно введені дані");
        }
    }


    public void OrderMenu() {
        OrderRepository orderRepository = new OrderRepository();
        List<OrderEntity> orderEntityList = orderRepository.get();
        for (OrderEntity o:orderEntityList) {
            System.out.println(o);
        }
        System.out.println("Введіть id  для підтвердження замовлення або 0 для виходу");
        int id = scanner.nextInt();
        if (id == 0) {
            return;
        }
        orderRepository.update(id);
        System.out.println("Замовлення підтверджено");
    }

    public void ProductsMenu(){
        ProductRepository productRepository = new ProductRepository();
        System.out.println("Редагувати дані про продукт - 1 ");
        System.out.println("Додати новий продукт - 2");
        int choice = scanner.nextInt();
        scanner.nextLine();
        List<ProductEntity> productEntities = productRepository.get();
        if(choice == 1){
            for (ProductEntity p:productEntities) {
                System.out.println(p);
            }
            System.out.println("Введіть id продукту");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Виберіть що хочите змінити:\nmodel\nprice");
            String edit = scanner.next();
            if (edit.equals("model")) {
                System.out.println("Введіть нову модель");
                String newModel = scanner.nextLine();
                productRepository.updateModel(id, newModel);
                System.out.println("Дані змінено");
            } else if (edit.equals("price")) {
                System.out.println("Введіть нову ціну");
                int newPrice = scanner.nextInt();
                scanner.nextLine();
                productRepository.updatePrice(id, newPrice);
                System.out.println("Дані змінено");
            }
        }
        else if(choice == 2){
            System.out.println("Введіть модель");
            String model = scanner.nextLine();
            System.out.println("Ввведіть ціну");
            int price = scanner.nextInt();
            ProductEntity product = new ProductEntity("Iphone", model, price);
            productRepository.add(product);
            System.out.println("Продукт додано");
        }
        else {
            System.out.println("Некоректний ввід");
        }
    }
}
