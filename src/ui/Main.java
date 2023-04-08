package ui;

import exceptions.DateFormatException;
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
        showMainMenu();
        //load the information
        //productList.load();
    }

    /**
     * The method showMainMenu, displays the main menu, allowing the user to choose between product management, order management and exit.
     */
    public static void showMainMenu() throws IOException {
        // Display welcome message
        System.out.println(BLUE + "***************************************************************" + RESET);
        System.out.println(BOLD + YELLOW + "                   WELCOME TO MERCADO LIBRE                    " + RESET);
        System.out.println(BLUE + "***************************************************************" + RESET);

        // Initialize stop flag for menu loop
        boolean stopFlag = false;

        // Display main menu options until user chooses to exit
        while (!stopFlag) {
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
                        System.out.println(BLUE + "***************************************************************" + RESET);
                        break;
                }
            } catch (InputMismatchException | NegativeNumberException | EmptyFileException | IncompleteDataException ex) {
                System.out.println(BLUE + "***************************************************************" + RESET);
                System.out.println(BOLD + YELLOW + "      INVALID INPUT: PLEASE ENTER AN INTEGER VALUE       ");
                System.out.println(BLUE + "***************************************************************" + RESET);
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
            // Display menu options
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
                System.out.println(BLUE + "***************************************************************" + RESET);

                // Determine action based on user input
                sc.nextLine();
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
                        showProductSearchMenu();
                        stopFlag = true;
                        break;
                    case 5:
                        // Return to the main menu
                        stopFlag = true;
                        break;
                    default:
                        // If user selects an option that is not available, display an error message
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
            // Display menu options
            System.out.println(BOLD + UNDERLINE + "ORDER MANAGEMENT MENU:" + RESET +
                    BOLD + YELLOW + "\n[1]" + RESET + " Add new order" +
                    BOLD + YELLOW + "\n[2]" + RESET + " Delete order" +
                    BOLD + YELLOW + "\n[3]" + RESET + " Search order" +
                    BOLD + YELLOW + "\n[4]" + RESET + " Return to the main menu");
            try {
                // Read user input from console
                System.out.print(BOLD + YELLOW + "> " + RESET);
                int mainOption = sc.nextInt();
                System.out.println(BLUE + "***************************************************************" + RESET);

                // Determine action based on user input
                sc.nextLine();
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
                        showOrderSearchMenu();
                        stopFlag = true;
                        break;
                    case 4:
                        // Return to the main menu
                        stopFlag = true;
                        break;
                    default:
                        // If user selects an option that is not available, display an error message
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
     * The method showProductSearchMenu, displays the product search menu, allowing the user to choose between several options.
     */
    public static void showProductSearchMenu() throws IOException{
        // Initialize stop flag for menu loop
        boolean stopFlag = false;

        // Menu loop
        while (!stopFlag) {
            // Display menu options
            System.out.println(BOLD + UNDERLINE + "PRODUCT SEARCH MENU:" + RESET +
                    BOLD + YELLOW + "\n[1]" + RESET + " Search by name" +
                    BOLD + YELLOW + "\n[2]" + RESET + " Search by price" +
                    BOLD + YELLOW + "\n[3]" + RESET + " search by category" +
                    BOLD + YELLOW + "\n[4]" + RESET + " Search by number of times purchased" +
                    BOLD + YELLOW + "\n[5]" + RESET + " Return to the main menu");
            try {
                // Read user input from console
                System.out.print(BOLD + YELLOW + "> " + RESET);
                int option = sc.nextInt();
                System.out.println(BLUE + "***************************************************************" + RESET);

                // Determine action based on user input
                sc.nextLine();
                switch (option) {
                    case 1:
                        controller.loadProductList();
                        // Search by name
                        System.out.println("Please enter the name of the product");
                        System.out.print(BOLD + YELLOW + "> " + RESET);
                        String name = sc.nextLine();
                        System.out.println(controller.searchProduct(option, name));
                        System.out.println(BLUE + "***************************************************************" + RESET);
                        stopFlag = true;
                        break;
                    case 2:
                        controller.loadProductList();
                        // Search by price
                        System.out.println("Please enter the price of the product");
                        System.out.print(BOLD + YELLOW + "> " + RESET);
                        String price = sc.nextLine();
                        System.out.println(controller.searchProduct(option, price));
                        System.out.println(BLUE + "***************************************************************" + RESET);
                        stopFlag = true;
                        break;
                    case 3:
                        controller.loadProductList();
                        // Search by category
                        System.out.println("Please select a category ");
                        System.out.print(BOLD + YELLOW + "> " + RESET);
                        String category = sc.nextLine();
                        System.out.println(controller.searchProduct(option, category));
                        System.out.println(BLUE + "***************************************************************" + RESET);
                        stopFlag = true;
                        break;
                    case 4:
                        controller.loadProductList();
                        // Search by number of times purchased
                        System.out.println("Please enter the number of times purchased");
                        System.out.print(BOLD + YELLOW + "> " + RESET);
                        String purchasedNum = sc.nextLine();
                        System.out.println(controller.searchProduct(option, purchasedNum));
                        System.out.println(BLUE + "***************************************************************" + RESET);
                        stopFlag = true;
                        break;
                    case 5:
                        // Return to the main menu
                        stopFlag = true;
                        break;
                    default:
                        // If user selects an option that is not available, display an error message
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
     * The method showOrderSearchMenu, displays the order search menu, allowing the user to choose between several options.
     */
    public static void showOrderSearchMenu() throws IOException{
        // Initialize stop flag for menu loop
        boolean stopFlag = false;

        // Menu loop
        while (!stopFlag) {
            // Display menu options
            System.out.println(BOLD + UNDERLINE + "PRODUCT SEARCH MENU:" + RESET +
                    BOLD + YELLOW + "\n[1]" + RESET + " Search by buyer name" +
                    BOLD + YELLOW + "\n[2]" + RESET + " Search by total price" +
                    BOLD + YELLOW + "\n[3]" + RESET + " Search by purchased date" +
                    BOLD + YELLOW + "\n[4]" + RESET + " Return to the main menu");
            try {
                // Read user input from console
                System.out.print(BOLD + YELLOW + "> " + RESET);
                int option = sc.nextInt();
                System.out.println(BLUE + "***************************************************************" + RESET);

                // Determine action based on user input
                sc.nextLine();
                switch (option) {
                    case 1:
                        controller.loadOrderList();
                        System.out.println("Please enter the name of the buyer");
                        System.out.print(BOLD + YELLOW + "> " + RESET);
                        String buyerName = sc.nextLine();
                        System.out.println(controller.searchOrder(option, buyerName));
                        System.out.println(BLUE + "***************************************************************" + RESET);
                        stopFlag = true;
                        break;
                    case 2:
                        controller.loadOrderList();
                        System.out.println("Please enter the order total price");
                        System.out.print(BOLD + YELLOW + "> " + RESET);
                        String totalPrice = sc.nextLine();
                        System.out.println(controller.searchOrder(option, totalPrice));
                        System.out.println(BLUE + "***************************************************************" + RESET);
                        stopFlag = true;
                        break;
                    case 3:
                        controller.loadOrderList();
                        System.out.println("Please enter the purchased date");
                        System.out.print(BOLD + YELLOW + "> " + RESET);
                        String purchasedDate = sc.nextLine();
                        System.out.println(controller.searchOrder(option, purchasedDate));
                        System.out.println(BLUE + "***************************************************************" + RESET);
                        stopFlag = true;
                        break;
                    case 4:
                        // Return to the main menu
                        stopFlag = true;
                        break;
                    default:
                        // If user selects an option that is not available, display an error message
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

    private static void addProduct() throws IOException {
        //Name++Description++Price++Quantityavailable++Category++purchasedNumber
        System.out.println("type the input with the format Name++Description++Price++Quantityavailable++purchasedNumber");
        String input = sc.nextLine();
        System.out.println("1. BOOKS\n2. ELECTRONIC\n3. CLOTHES_AND_ACCESORIES\n4. FOOD_AND_DRINKS\n5. PAPELERY" +
                "\n6. SPORTS\n7. BEAUTY_AND_PERSONAL_CARE_PRODUCTS\n8. TOYS_AND_GAMES\nEnter a category: ");
        try{
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
                    break;
                default:
                    System.out.println("Please enter a valid option.");
                    break;
            }
            controller.addProduct(input, category);
        } catch (InputMismatchException | EmptyFileException | IncompleteDataException | NegativeNumberException ex){
            System.out.println(BLUE + "***************************************************************" + RESET);
            System.out.println(BOLD + YELLOW + ex.getMessage());
            System.out.println(BLUE + "***************************************************************" + RESET);
        }

    }

    private static void addOrder() throws IOException {
        //buyerName++productList++totalPrice++purchaseDate
        controller.loadProductList();
        System.out.println("PRODUCTS LIST");
        controller.showProductList();
        System.out.println("NOTE: Enter the purchase Date with the format YYYY-MM-DD");
        System.out.println("NOTE: Write the quantity of each corresponding product with the format NUM,NUM");
        System.out.println("Type the input with the format buyerName++productList++ProductQuantity++totalPrice++purchaseDate");
        try{
            String input = sc.nextLine();
            controller.addOrder(input);
        } catch (IncompleteDataException | NegativeNumberException | DateFormatException ex){
            System.out.println(BLUE + "***************************************************************" + RESET);
            System.out.println(BOLD + YELLOW + ex.getMessage());
            System.out.println(BLUE + "***************************************************************" + RESET);
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