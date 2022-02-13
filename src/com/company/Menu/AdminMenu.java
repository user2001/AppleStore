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

public class AdminMenu implements LoginInterface, Menu {

    private static final int CHANGE = 1;
    private static final int ADD = 2;

    Scanner scanner = new Scanner(System.in);
    AdminEntity admin;

    private String[] items = {"Users menu-'1'", "Order menu-'2'","Products menu-'3'","Main menu-'0'"};

    public void helloMenu() {
        showItems(items);
    }

    public void choiceRole() {
        while (true) {
            helloMenu();
            int chooseNumber = scanner.nextInt();
            scanner.nextLine();
            switch (chooseNumber) {
                case 1:
                    usersMenu();
                    break;
                case 2:
                    ordersMenu();
                    break;
                case 3:
                    productsMenu();
                    break;
                case 0:
                    new LoginMenu().choiceRole();
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
        admin = adminRepository.getAdmin(login, password);
            if (admin != null) {
                System.out.println("Успішний вхід");
                choiceRole();
            } else {
                System.out.println("Некоректно введені дані або користувача неіснує");
            }
    }


    public void usersMenu() {
        UserRepository userRepository = new UserRepository();
        List<UserEntity> userEntityList = userRepository.get();
        userEntityList.forEach(System.out::println);

        System.out.println("Введіть id користувача та параметр: \nBlock для блокування\nUnblock  для розблокування");
        String input = scanner.nextLine();
        String[] arr = input.split(" ");
        int id = Integer.parseInt(arr[0]);
        String param = arr[1];
        var user = userEntityList.stream()
                .filter(it -> it.getId() == id)
                .findFirst().get();

        if ("Block".equals(param)) {
            userRepository.update(param, id);
            System.out.println("Користувач " + user.getLogin() + " заблокований");
        } else if ("Unblock".equals(param)) {
            userRepository.update(param, id);
            System.out.println("Користувач " + user.getLogin() + " розблокований");
        } else {
            System.out.println("Некоректно введені дані");
        }
    }


    public void ordersMenu() {
        OrderRepository orderRepository = new OrderRepository();
        List<OrderEntity> orderEntityList = orderRepository.get();
        orderEntityList.forEach(System.out::println);

        System.out.println("Введіть id  для підтвердження замовлення або 0 для виходу");
        int id = scanner.nextInt();

        if (id == 0) {
            return;
        }
        orderRepository.update(id);
        System.out.println("Замовлення підтверджено");
    }

    public void productsMenu(){
        ProductRepository productRepository = new ProductRepository();

        System.out.println("Редагувати дані про продукт - 1 ");
        System.out.println("Додати новий продукт - 2");
        int input = scanner.nextInt();
        scanner.nextLine();
        List<ProductEntity> productEntities = productRepository.get();

        if(input == CHANGE){
            changeProductCharacteristic(productEntities,productRepository);
        }
        else if(input == ADD){
            addProduct(productRepository);
        }
        else {
            System.out.println("Некоректний ввід");
        }
    }

    public void changeProductCharacteristic(List<ProductEntity> productEntities,ProductRepository productRepository){
        productEntities.forEach(System.out::println);
        System.out.println("Введіть id продукту");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Виберіть що хочите змінити:\nmodel\nprice");
        String edit = scanner.next();

        if ("model".equals(edit)) {
            System.out.println("Введіть нову модель");
            scanner.nextLine();
            String newModel = scanner.nextLine();
            productRepository.updateModel(id, newModel);
            System.out.println("Дані змінено");
        } else if ("price".equals(edit)) {
            System.out.println("Введіть нову ціну");
            int newPrice = scanner.nextInt();
            scanner.nextLine();
            productRepository.updatePrice(id, newPrice);
            System.out.println("Дані змінено");
        }
    }

    public void addProduct(ProductRepository productRepository) {
        System.out.println("Введіть модель");
        String model = scanner.nextLine();
        System.out.println("Ввведіть ціну");
        int price = scanner.nextInt();
        ProductEntity product = new ProductEntity("Iphone", model, price);
        productRepository.add(product);
        System.out.println("Продукт додано");
    }
}
