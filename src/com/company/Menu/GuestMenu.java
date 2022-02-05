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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GuestMenu implements LoginInterface {
    Scanner scanner = new Scanner(System.in);
    List<ProductEntity> choosenProducts = new ArrayList<>();
    ProductEntity choosenProduct;
    List<ProductEntity> cart = new ArrayList<>();
    UserEntity user = new UserEntity();

    public void helloMenu() {
        System.out.println("Products menu-'1'");
        System.out.println("Search products in range-'2'");
        System.out.println("My orders menu-'3'");
        System.out.println("Корзина-'4'");
        System.out.println("Exit-'0'");
    }

    public void choiceRole() throws SQLException {
        while (true) {
            helloMenu();
            int chooseNumber = scanner.nextInt();
            switch (chooseNumber) {
                case 1:
                    showProducts();
                    break;
                case 2:
                    showProductsInRange();
                    break;
                case 3:
                    chooseProduct();
                    break;
                case 4:
                    myCart();
                    // id замовлень. меню адміна. переглянутию підтвердити. написати
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
        UserRepository userRepository = new UserRepository();
        try {
            if ((user = userRepository.getUser(login, password)) != null ) {
                if(!user.getStatus().equals("Block")) {
                    System.out.println("Успішний вхід");
                    choiceRole();
                }
                else {
                    System.out.println("Цей користувач заблокований");
                }
            } else {
                System.out.println("Некоректно введені дані або користувача неіснує");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showProducts() {
        ProductRepository productRepository = new ProductRepository();
        List<ProductEntity> productList;
        productList = productRepository.get();
        System.out.println(productList);
    }

    public void showProductsInRange() throws SQLException {
        System.out.println("Введіть мінімальну ціну");
        int min = scanner.nextInt();
        System.out.println("Введіть максимальну ціну");
        int max = scanner.nextInt();
        scanner.nextLine();
        ProductRepository productRepository = new ProductRepository();
        List<ProductEntity> productList;
        productList = productRepository.getProductsInRange(min,max);
        System.out.println(productList);
    }

    public void chooseProduct() {
        ProductRepository productRepository = new ProductRepository();
        List<ProductEntity> productList;
        productList = productRepository.get();
        Comparator<ProductEntity> comparator = Comparator.comparing(ProductEntity::getId);
        if (!choosenProducts.isEmpty()) {
            productList = productList.stream().filter(i1 -> choosenProducts.stream().allMatch(i2 -> i1.getId() != i2.getId())).collect(Collectors.toList());
        }
        if (productList.isEmpty()) {
            System.out.println("Товари закінчилися");
            return;
        }
        System.out.println(productList);
        System.out.println("Вкажіть id товару");
        int needProduct = scanner.nextInt();
        choosenProduct = productList.stream()
                .filter(product -> product.getId() == needProduct).findFirst().orElse(null);
        System.out.println(choosenProduct);
        if (!choosenProduct.equals(null)) {
            choosenProducts.add(choosenProduct);
            System.out.println("Ви додали до вашої корзини: " + choosenProduct);
            cart.add(choosenProduct);
            cart.sort(comparator);
        }
        else{
            System.out.println("Некоректно введений id продутку");
        }

    }

    public void myCart() {
        System.out.println("Ваше замовлення" + cart);
        System.out.println("Підтвердити замовлення? 1 - так, 0 - ні ");
        int check;
        check = scanner.nextInt();
        if (check == 0) {
            return;
        }
        OrderRepository orderRepository = new OrderRepository();
        String productsId = "";
        int fullPrice = 0;
        for (ProductEntity p : choosenProducts) {
            productsId += p.getId() + ", ";
        }
        productsId = productsId.strip();
        productsId = productsId.substring(0, productsId.length() - 1);
        for (ProductEntity p : choosenProducts) {
            fullPrice += p.getPrice();
        }
        orderRepository.makeOrder(new OrderEntity(user.getId(), productsId, fullPrice, "Uncomfirmed"));
        System.out.println("Замовлення додано");

    }

    public void showAdmins() {
        AdminRepository adminRepository = new AdminRepository();
        List<AdminEntity> adminList;
        adminList = adminRepository.get();
        System.out.println(adminList);
    }
    /// треба перевірку чи є такий юзер в базі даних, якщо нема то опція: повторити спробу або зареєструватись
}
