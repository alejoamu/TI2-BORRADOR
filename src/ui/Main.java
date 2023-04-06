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
    public static final Controller controller = new Controller();

    // Colors for console output
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String UNDERLINE = "\u001B[4m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";

    public static void main(String[] args) throws IOException {
        //showMainMenu();
        //load the information
        //productList.load();
        boolean stopFlag = false;
        while (!stopFlag) {
            try {
                System.out.println("Please select an option");
                System.out.println("\n1. Add menu\n2. Delete menu\n3. Search menu\n4. Change quantity of product\n5. Exit");
                int option = sc.nextInt();
                sc.nextLine();
                // Determine action based on user input
                try {
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
                } catch (IncompleteDataException | NegativeNumberException | EmptyFileException ex) {
                    System.out.println(ex.getMessage());
                }
            } catch (InputMismatchException ex) {
                System.out.println("Must enter a numeric data."); // Revisar si tenemos que crear esta excepción
            }
        }
    }

    /**
     * The method showMainMenu, displays the main menu, allowing the user to choose between product management, order management and exit.
     */
    public static void showMainMenu() throws IOException {
        // Display welcome message
        System.out.println(BLUE + "***************************************************************" + RESET);
        System.out.println(BOLD + YELLOW + "                   WELCOME TO MERCADO LIBRE                    " + RESET);

        // Initialize stop flag for menu loop
        boolean stopFlag = false;

        // Display main menu options until user chooses to exit
        while (!stopFlag) {
            System.out.println(BLUE + "***************************************************************" + RESET);
            System.out.println(BOLD + UNDERLINE + "MAIN MENU:" + RESET +
                    BOLD + YELLOW + "\n[1]" + RESET + " Product management" +
                    BOLD + YELLOW + "\n[2]" + RESET + " Order management" +
                    BOLD + YELLOW + "\n[3]" + RESET + " Exit"
            );

            try {
                // Read user input from console
                System.out.print(BOLD + YELLOW + "> " + RESET);
                int mainOption = sc.nextInt();
                System.out.println(BLUE + "***************************************************************" + RESET);

                // Determine action based on user input
                switch (mainOption) {
                    case 1:
                        // Product management
                        showProductManagementMenu();
                        break;
                    case 2:
                        // Order management
                        showOrderManagementMenu();
                        break;
                    case 3:
                        System.out.println(BOLD + YELLOW + "                      EXIT SUCCESSFULLY                        ");
                        System.out.println(BLUE + "***************************************************************" + RESET);
                        stopFlag = true;
                        break;
                    default:
                        // Handle invalid input
                        System.out.println(BOLD + YELLOW + "                     OPTION NOT AVAILABLE                      ");
                        break;
                }
            } catch (InputMismatchException ex) {
                System.out.println(BLUE + "***************************************************************" + RESET);
                System.out.println(BOLD + YELLOW + "      INVALID INPUT: PLEASE ENTER AN INTEGER VALUE       ");
                sc.nextLine(); // Consume the invalid input
            }
        }
    }

    /**
     * The method showProductManagementMenu, displays the product management menu, allowing the user to choose between several options.
     */
    public static void showProductManagementMenu() throws IOException {
        // Initialize stop flag for menu loop
        boolean stopFlag = false;

        // Menu loop
        while (!stopFlag) {
            // Display menu options and current game state
            System.out.println(BOLD + UNDERLINE + "PRODUCT MANAGEMENT MENU:" + RESET +
                    BOLD + YELLOW + "\n[1]" + RESET + " Add new product" +
                    BOLD + YELLOW + "\n[2]" + RESET + " Delete product" +
                    BOLD + YELLOW + "\n[3]" + RESET + " Increase the available quantity of a product" +
                    BOLD + YELLOW + "\n[4]" + RESET + " Search product" +
                    BOLD + YELLOW + "\n[5]" + RESET + " Return to the main menu");
            try {
                // Read user input from console
                System.out.print(BOLD + YELLOW + "> " + RESET);
                int mainOption = sc.nextInt();

                // Determine action based on user input
                switch (mainOption) {
                    case 1:
                        // Add new product
                        addProduct();
                        stopFlag = true;
                        break;
                    case 2:
                        // Delete product
                        deleteProduct();
                        stopFlag = true;
                        break;
                    case 3:
                        // Increase quantity
                        changeQuantity();
                        stopFlag = true;
                        break;
                    case 4:
                        // Search product

                        stopFlag = true;
                        break;
                    case 5:
                        // Return to the main menu
                        stopFlag = true;
                        break;
                    default:
                        // If user selects an option that is not available, display an error message
                        System.out.println(BLUE + "***************************************************************" + RESET);
                        System.out.println(BOLD + YELLOW + "                     OPTION NOT AVAILABLE                      ");
                        System.out.println(BLUE + "***************************************************************" + RESET);
                        break;
                }
            } catch (InputMismatchException ex) {
                System.out.println(BLUE + "***************************************************************" + RESET);
                System.out.println(BOLD + YELLOW + "      INVALID INPUT: PLEASE ENTER AN INTEGER VALUE       ");
                System.out.println(BLUE + "***************************************************************" + RESET);
                sc.nextLine(); // Consume the invalid input
            }
        }
    }

    /**
     * The method showOrderManagementMenu, displays the order management menu, allowing the user to choose between several options.
     */
    public static void showOrderManagementMenu() throws IOException {
        // Initialize stop flag for menu loop
        boolean stopFlag = false;

        // Menu loop
        while (!stopFlag) {
            // Display menu options and current game state
            System.out.println(BOLD + UNDERLINE + "ORDER MANAGEMENT MENU:" + RESET +
                    BOLD + YELLOW + "\n[1]" + RESET + " Add new order" +
                    BOLD + YELLOW + "\n[2]" + RESET + " Delete order" +
                    BOLD + YELLOW + "\n[3]" + RESET + " Search order" +
                    BOLD + YELLOW + "\n[4]" + RESET + " Return to the main menu");
            try {
                // Read user input from console
                System.out.print(BOLD + YELLOW + "> " + RESET);
                int mainOption = sc.nextInt();

                // Determine action based on user input
                switch (mainOption) {
                    case 1:
                        // Add new order
                        addOrder();
                        stopFlag = true;
                        break;
                    case 2:
                        // Delete order
                        deleteOrder();
                        stopFlag = true;
                        break;
                    case 3:
                        // Search order

                        stopFlag = true;
                        break;
                    case 4:
                        // Return to the main menu
                        stopFlag = true;
                        break;
                    default:
                        // If user selects an option that is not available, display an error message
                        System.out.println(BLUE + "***************************************************************" + RESET);
                        System.out.println(BOLD + YELLOW + "                     OPTION NOT AVAILABLE                      ");
                        System.out.println(BLUE + "***************************************************************" + RESET);
                        break;
                }
            } catch (InputMismatchException ex) {
                System.out.println(BLUE + "***************************************************************" + RESET);
                System.out.println(BOLD + YELLOW + "      INVALID INPUT: PLEASE ENTER AN INTEGER VALUE       ");
                System.out.println(BLUE + "***************************************************************" + RESET);
                sc.nextLine(); // Consume the invalid input
            }
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
        //buyerName++productList++totalPrice++purchaseDate
        sc.nextLine();
        System.out.println("type the input with the format buyerName++productList++totalPrice++purchaseDate");
        System.out.println("Enter the purchase Date with the format YYYY-MM-DD");
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