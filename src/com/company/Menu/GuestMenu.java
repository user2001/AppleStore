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

public class GuestMenu implements LoginInterface, Menu {
    Scanner scanner = new Scanner(System.in);
    List<ProductEntity> choosenProducts = new ArrayList<>();
    ProductEntity choosenProduct;
    List<ProductEntity> cart = new ArrayList<>();
    UserEntity user;

    private String[] items = {"Products menu-'1'", "Search products in range-'2'","My orders menu-'3'","My basket-'4'","Main menu-'0'"};


    public void helloMenu() {
       showItems(items);
    }

    public void choiceRole(){
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
                    myBasket();
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
        UserRepository userRepository = new UserRepository();
        try {
            if ((user = userRepository.getUser(login, password)) != null ) {
                if(!user.getStatus().equals("Block")) {
                    System.out.println("???????????????? ????????");
                    choiceRole();
                }
                else {
                    System.out.println("?????? ???????????????????? ????????????????????????");
                }
            } else {
                System.out.println("???????????????????? ?????????????? ???????? ?????? ?????????????????????? ?????????????? ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showProducts() {
        ProductRepository productRepository = new ProductRepository();
        List<ProductEntity> productList;
        productList = productRepository.get();
        productList.forEach(System.out::println);
    }

    public void showProductsInRange(){
        System.out.println("?????????????? ???????????????????? ????????");
        int min = scanner.nextInt();
        System.out.println("?????????????? ?????????????????????? ????????");
        int max = scanner.nextInt();
        scanner.nextLine();
        ProductRepository productRepository = new ProductRepository();
        List<ProductEntity> productList;
        productList = productRepository.getProductsInRange(min,max);
        productList.forEach(System.out::println);
    }

    public void chooseProduct() {
        ProductRepository productRepository = new ProductRepository();
        List<ProductEntity> productList;
        productList = productRepository.get();
        Comparator<ProductEntity> comparator = Comparator.comparing(ProductEntity::getId);

        if (!choosenProducts.isEmpty()) {
            productList = productList.stream()
                    .filter(i1 -> choosenProducts.stream()
                            .allMatch(i2 -> i1.getId() != i2.getId()))
                    .collect(Collectors.toList());
        }
        if (productList.isEmpty()) {
            System.out.println("???????????? ??????????????????????");
            return;
        }
        productList.forEach(System.out::println);
        System.out.println("?????????????? id ????????????");
        int needProduct = scanner.nextInt();
        choosenProduct = productList.stream()
                .filter(product -> product.getId() == needProduct)
                .findFirst()
                .orElse(null);
        System.out.println(choosenProduct);

        if (!choosenProduct.equals(null)) {
            choosenProducts.add(choosenProduct);
            System.out.println("???? ???????????? ???? ?????????? ??????????????: " + choosenProduct);
            cart.add(choosenProduct);
            cart.sort(comparator);
        }
        else{
            System.out.println("???????????????????? ???????????????? id ????????????????");
        }

    }

    public void myBasket() {
        System.out.println("???????? ????????????????????" + cart);
        System.out.println("?????????????????????? ????????????????????? 1 - ??????, 0 - ???? ");
        int check;
        check = scanner.nextInt();

        if (check == 0) {
            return;
        }
        OrderRepository orderRepository = new OrderRepository();
        String productsId = "";
        for (ProductEntity p : choosenProducts) {
            productsId += p.getId() + ", ";
        }
        productsId = productsId.strip();
        productsId = productsId.substring(0, productsId.length() - 1);

        var fullPrice = (Integer) choosenProducts.stream()
                .map(ProductEntity::getPrice)
                .mapToInt(Integer::intValue)
                .sum();

        orderRepository.makeOrder(new OrderEntity(user.getId(), productsId, fullPrice, "Uncomfirmed"));
        System.out.println("???????????????????? ????????????");

    }

}
