package ui;

import Model.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static final Scanner sc = new Scanner(System.in);
    static ProductList productList = new ProductList();
    static OrderList orderList = new OrderList();

    public static void main(String[] args) throws IOException {
        //load the information
        //productList.load();

        boolean stopFlag = false;
        while (!stopFlag) {
            System.out.println("Please select an option");
            System.out.println("\n1. Add menu\n2. Delete menu\n3. Search menu\n4. Change quantity of product\n5. Exit");
            int option = sc.nextInt();
            sc.nextLine();
            // Determine action based on user input
            switch (option) {
                case 1:
                    addMenu();
                    break;
                case 2:
                    deleteMenu();
                    break;
                case 3:
                    searchMenu();
                    break;
                case 4:
                    productList.load();
                    changeQuantity();
                    break;
                case 5:
                    System.out.println("\t\tEXIT SUCCESSFULLY");
                    stopFlag = true;
                    break;
                default:
                    // Handle invalid input
                    System.out.println("OPTION NOT AVAILABLE");
                    break;
            }
        }
    }

    public static void addMenu() throws IOException {
        System.out.println("Please select an add option");
        System.out.println("\n1. Add a product\n2. Add an order");
        int option = sc.nextInt();
        String input = "";
        switch (option) {
            case 1:
                //Name++Description++Price++Quantityavailable++Category++purchasedNumber
                sc.nextLine();
                System.out.println("type the input with the format Name++Description++Price++Quantityavailable++purchasedNumber");
                input = sc.nextLine();
                System.out.println(" BOOKS, ELECTRONIC, CLOTHES_AND_ACCESORIES");
                int optionCategory = sc.nextInt();
                Category category = null;
                switch (optionCategory){
                    case 1:
                       category = Category.BOOKS;
                       break;
                }
                String[] data = input.split("\\+\\+");
                System.out.println(Arrays.toString(data));
                productList.getProducts().add(
                        new Product(data[0], data[1], Double.parseDouble(data[2]), Integer.parseInt(data[3]), category, Integer.parseInt(data[4]))
                );
                productList.save();
                break;
            case 2:
                //buyerName++productList++totalPrice++purchasedDate
                sc.nextLine();
                System.out.println("type the input with the format buyerName++productList++totalPrice");
                input = sc.nextLine();
                String[] dataOrders = input.split("\\+\\+");
                System.out.println(Arrays.toString(dataOrders));
                orderList.getOrders().add(
                        new Order(dataOrders[0], dataOrders[1], Double.parseDouble(dataOrders[2]))
                );
                orderList.save();
                break;
        }
    }

    public static void deleteMenu() throws IOException {
        System.out.println("Please select a delete option");
        System.out.println("\n1. Delete a product\n2. Delete an order");
        int option = sc.nextInt();
        sc.nextLine();
        switch (option) {
            case 1:
                productList.load();
                productList.show();
                System.out.println("Please enter the name of the product to delete");
                String productName = sc.nextLine();
                productList.DeleteProduct(productName);
                break;
            case 2:
                orderList.load();
                orderList.show();
                System.out.println("Please enter the buyer name of the order to delete");
                String buyerName = sc.nextLine();
                orderList.DeleteOrder(buyerName);
                break;
        }
    }

    public static void searchMenu() throws IOException { // Metodo para saber si se busca un producto o una orden
        System.out.println("Please select a search option");
        System.out.println("\n1. Search a product\n2. Search an order");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                productList.load();
                typeOfProductSearchMenu();
                break;
            case 2:
                orderList.load();
                typeOfOrderSearchMenu();
                break;
        }
    }

    public static void typeOfProductSearchMenu() { //Metodo para buscar el producto dependiendo del dato
        System.out.println("Please select a search option");
        System.out.println("\n1. Search by name\n2. Search by price\n3. search by category\n4. Search by number of times purchased.");

        int option = sc.nextInt();
        sc.nextLine();
        switch (option) {
            case 1:
                System.out.println("Please enter the name of the product");
                String name = sc.nextLine();
                productList.searchProduct(option, name);
                break;
            case 2:
                System.out.println("Please enter the price of the product");
                String price = sc.nextLine();
                productList.searchProduct(option, price);
                break;
            case 3:
                System.out.println("Please select a category ");
                String category = sc.nextLine();
                productList.searchProduct(option, category);
                break;
            case 4:
                System.out.println("Please enter the number of times purchased");
                String purchasedNum = sc.nextLine();
                productList.searchProduct(option, purchasedNum);
                break;
        }
    }

    public static void typeOfOrderSearchMenu() { //Metodo para buscar la orden dependiendo del dato
        System.out.println("Please select a search option");
        System.out.println("\n1. Search by buyer name\n2. Search by total price\n3. search by purchased date.");

        int option = sc.nextInt();
        sc.nextLine();
        switch (option) {
            case 1:
                System.out.println("Please enter the name of the buyer");
                String buyerName = sc.nextLine();
                orderList.searchOrder(option, buyerName);
                break;
            case 2:
                System.out.println("Please enter the order total price");
                String totalPrice = sc.nextLine();
                orderList.searchOrder(option, totalPrice);
                break;
            case 3:
                System.out.println("Please enther the purchased date");
                String purchasedDate = sc.nextLine();
                orderList.searchOrder(option, purchasedDate);
                break;
        }
    }
    public static void changeQuantity()throws IOException{
        productList.show();
        System.out.println("Please type the name of the product to change their quantity");
        String product = sc.nextLine();
        productList.showQuantity(product);
        System.out.println("Please type the new available quantity of this product");
        int newQuantity = sc.nextInt();
        productList.changeQuantity(product, newQuantity);
    }
}
