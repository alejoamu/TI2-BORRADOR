package ui;

import exceptions.EmptyFileException;
import exceptions.NegativeNumberException;
import model.*;
import exceptions.IncompleteDataException;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static final Scanner sc = new Scanner(System.in);
    static Controller controller = new Controller();

    public static void main(String[] args) throws IOException {
        //load the information
        //productList.load();
        try{
            boolean stopFlag = false;
            while (!stopFlag) {
                System.out.println("Please select an option");
                System.out.println("\n1. Add menu\n2. Delete menu\n3. Search menu\n4. Change quantity of product\n5. Exit");
                int option = sc.nextInt();
                sc.nextLine();
                // Determine action based on user input
                try{
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
                }catch (IncompleteDataException | NegativeNumberException | EmptyFileException ex){
                    System.out.println(ex.getMessage());
                }

            }
        } catch (InputMismatchException ex){
            System.out.println("Debe ingresar un dato numérico"); // Revisar si tenemos que crear esta excepción
        }

    }

    public static void addMenu() throws IOException {
        System.out.println("Please select an add option");
        System.out.println("\n1. Add a product\n2. Add an order");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                addProduct();
                break;
            case 2:
                addOrder();
                break;
        }
    }

    private static void addProduct() throws IOException {
        //Name++Description++Price++Quantityavailable++Category++purchasedNumber
        sc.nextLine();
        System.out.println("type the input with the format Name++Description++Price++Quantityavailable++purchasedNumber");
        String input = sc.nextLine();
        System.out.println("1. BOOKS\n2. ELECTRONIC\n3. CLOTHES_AND_ACCESORIES\n4. FOOD_AND_DRINKS\n5. PAPELERY" +
                "\n6. SPORTS\n7. BEAUTY_AND_PERSONAL_CARE_PRODUCTS\n8. TOYS_AND_GAMES\nEnter a category: ");
        int optionCategory = sc.nextInt();
        Category category = null;
        switch (optionCategory) {
            case 1:
                category = Category.BOOKS;
                break;
            case 2:
                category = Category.ELECTRONIC;
                break;
            case 3:
                category = Category.CLOTHES_AND_ACCESORIES;
                break;
            case 4:
                category = Category.FOOD_AND_DRINKS;
                break;
            case 5:
                category = Category.PAPELERY;
                break;
            case 6:
                category = Category.SPORTS;
                break;
            case 7:
                category = Category.BEAUTY_AND_PERSONAL_CARE_PRODUCTS;
                break;
            case 8:
                category = Category.TOYS_AND_GAMES;
            default:
                System.out.println("Please enter a valid option.");
                break;
        }
        controller.addProduct(input, category);
    }

    private static void addOrder() throws IOException {
        //buyerName++productList++totalPrice++purchasedDate
        sc.nextLine();
        System.out.println("type the input with the format buyerName++productList++totalPrice");
        String input = sc.nextLine();
        controller.addOrder(input);
    }

    public static void deleteMenu() throws IOException {
        System.out.println("Please select a delete option");
        System.out.println("\n1. Delete a product\n2. Delete an order");
        int option = sc.nextInt();
        sc.nextLine();
        switch (option) {
            case 1:
                deleteProduct();
                break;
            case 2:
                deleteOrder();
                break;
        }
    }

    private static void deleteProduct() throws IOException {
        controller.loadProductList();
        controller.showProductList();
        System.out.println("Please enter the name of the product to delete");
        String productName = sc.nextLine();
        controller.deleteProduct(productName);
    }

    private static void deleteOrder() throws IOException {
        controller.loadOrderList();
        controller.showOrderList();
        System.out.println("Please enter the buyer name of the order to delete");
        String buyerName = sc.nextLine();
        System.out.println(controller.deleteOrder(buyerName));

    }

    public static void searchMenu() throws IOException { // Metodo para saber si se busca un producto o una orden
        System.out.println("Please select a search option");
        System.out.println("\n1. Search a product\n2. Search an order");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                controller.loadProductList();
                typeOfProductSearchMenu();
                break;
            case 2:
                controller.loadOrderList();
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
                controller.searchProduct(option, name);
                break;
            case 2:
                System.out.println("Please enter the price of the product");
                String price = sc.nextLine();
                controller.searchProduct(option, price);
                break;
            case 3:
                System.out.println("Please select a category ");
                String category = sc.nextLine();
                controller.searchProduct(option, category);
                break;
            case 4:
                System.out.println("Please enter the number of times purchased");
                String purchasedNum = sc.nextLine();
                controller.searchProduct(option, purchasedNum);
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
                controller.searchOrder(option, buyerName);
                break;
            case 2:
                System.out.println("Please enter the order total price");
                String totalPrice = sc.nextLine();
                controller.searchOrder(option, totalPrice);
                break;
            case 3:
                System.out.println("Please enther the purchased date");
                String purchasedDate = sc.nextLine();
                controller.searchOrder(option, purchasedDate);
                break;
        }
    }

    public static void changeQuantity() throws IOException {
        controller.loadProductList();
        controller.showProductList();
        System.out.println("Please type the name of the product to change their quantity");
        String product = sc.nextLine();
        controller.showProductQuantity(product);
        System.out.println("Please type the new available quantity of this product");
        int newQuantity = sc.nextInt();
        controller.changeProductQuantity(product, newQuantity);
    }
}
