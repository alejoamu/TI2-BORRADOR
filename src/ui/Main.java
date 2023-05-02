package ui;

import color.Color;
import exceptions.DateFormatException;
import exceptions.EmptyFileException;
import exceptions.IncompleteDataException;
import exceptions.NegativeNumberException;
import model.Controller;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static final Scanner sc = new Scanner(System.in);
    public static final Controller controller = new Controller();
    public static final String category = controller.getCategory();

    public static void main(String[] args) throws IOException {
        Main manager = new Main();
        manager.showMainMenu();
    }

    /**
     * The method showMainMenu, displays the main menu, allowing the user to choose between product management, order management and exit.
     */
    public void showMainMenu() throws IOException {
        // Display welcome message
        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
        System.out.println(Color.BOLD + Color.YELLOW + "                   WELCOME TO MERCADO LIBRE                    " + Color.RESET);
        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);

        // Initialize stop flag for menu loop
        boolean stopFlag = false;

        // Display main menu options until user chooses to exit
        while (!stopFlag) {
            System.out.println(Color.BOLD + Color.UNDERLINE + "MAIN MENU:" + Color.RESET +
                    Color.BOLD + Color.YELLOW + "\n[1]" + Color.RESET + " Product management" +
                    Color.BOLD + Color.YELLOW + "\n[2]" + Color.RESET + " Order management" +
                    Color.BOLD + Color.YELLOW + "\n[3]" + Color.RESET + " Exit"
            );
            try {
                // Read user input from console
                System.out.print(Color.BOLD + Color.YELLOW + "> " + Color.RESET);
                int mainOption = sc.nextInt();
                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);

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
                        System.out.println(Color.BOLD + Color.YELLOW + "                      EXIT SUCCESSFULLY                        " + Color.RESET);
                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                        stopFlag = true;
                        break;
                    default:
                        // Handle invalid input
                        System.out.println(Color.BOLD + Color.YELLOW + "                     OPTION NOT AVAILABLE                      " + Color.RESET);
                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                        break;
                }
            } catch (InputMismatchException | NegativeNumberException | EmptyFileException |
                     IncompleteDataException ex) {
                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                System.out.println(Color.BOLD + Color.YELLOW + "      INVALID INPUT: PLEASE ENTER AN INTEGER VALUE       " + Color.RESET);
                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                sc.nextLine(); // Consume the invalid input
            }
        }
    }

    /**
     * The method showProductManagementMenu, displays the product management menu, allowing the user to choose between several options.
     */
    public void showProductManagementMenu() throws IOException {
        // Initialize stop flag for menu loop
        boolean stopFlag = false;

        // Menu loop
        while (!stopFlag) {
            // Display menu options
            System.out.println(Color.BOLD + Color.UNDERLINE + "PRODUCT MANAGEMENT MENU:" + Color.RESET +
                    Color.BOLD + Color.YELLOW + "\n[1]" + Color.RESET + " Add new product" +
                    Color.BOLD + Color.YELLOW + "\n[2]" + Color.RESET + " Delete product" +
                    Color.BOLD + Color.YELLOW + "\n[3]" + Color.RESET + " Increase the available quantity of a product" +
                    Color.BOLD + Color.YELLOW + "\n[4]" + Color.RESET + " Search product" +
                    Color.BOLD + Color.YELLOW + "\n[5]" + Color.RESET + " Return to the main menu");
            try {
                // Read user input from console
                System.out.print(Color.BOLD + Color.YELLOW + "> " + Color.RESET);
                int mainOption = sc.nextInt();
                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);

                // Determine action based on user input
                sc.nextLine();
                switch (mainOption) {
                    case 1:
                        // Add new product
                        controller.loadProductList();
                        addProduct();
                        stopFlag = true;
                        break;
                    case 2:
                        // Delete product
                        controller.loadProductList();
                        deleteProduct();
                        stopFlag = true;
                        break;
                    case 3:
                        // Increase quantity
                        controller.loadProductList();
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
                        System.out.println(Color.BOLD + Color.YELLOW + "                     OPTION NOT AVAILABLE                      " + Color.RESET);
                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                        break;
                }
            } catch (InputMismatchException ex) {
                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                System.out.println(Color.BOLD + Color.YELLOW + "      INVALID INPUT: PLEASE ENTER AN INTEGER VALUE       " + Color.RESET);
                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                sc.nextLine(); // Consume the invalid input
            }
        }
    }

    /**
     * The method showOrderManagementMenu, displays the order management menu, allowing the user to choose between several options.
     */
    public void showOrderManagementMenu() throws IOException {
        // Initialize stop flag for menu loop
        boolean stopFlag = false;

        // Menu loop
        while (!stopFlag) {
            // Display menu options
            System.out.println(Color.BOLD + Color.UNDERLINE + "ORDER MANAGEMENT MENU:" + Color.RESET +
                    Color.BOLD + Color.YELLOW + "\n[1]" + Color.RESET + " Add new order" +
                    Color.BOLD + Color.YELLOW + "\n[2]" + Color.RESET + " Delete order" +
                    Color.BOLD + Color.YELLOW + "\n[3]" + Color.RESET + " Search order" +
                    Color.BOLD + Color.YELLOW + "\n[4]" + Color.RESET + " Return to the main menu");
            try {
                // Read user input from console
                System.out.print(Color.BOLD + Color.YELLOW + "> " + Color.RESET);
                int mainOption = sc.nextInt();
                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);

                // Determine action based on user input
                sc.nextLine();
                switch (mainOption) {
                    case 1:
                        // Add new order
                        controller.loadOrderList();
                        addOrder();
                        stopFlag = true;
                        break;
                    case 2:
                        // Delete order
                        controller.loadOrderList();
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
                        System.out.println(Color.BOLD + Color.YELLOW + "                     OPTION NOT AVAILABLE                      " + Color.RESET);
                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                        break;
                }
            } catch (InputMismatchException ex) {
                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                System.out.println(Color.BOLD + Color.YELLOW + "      INVALID INPUT: PLEASE ENTER AN INTEGER VALUE       " + Color.RESET);
                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                sc.nextLine(); // Consume the invalid input
            }
        }
    }

    /**
     * The method showProductSearchMenu, displays the product search menu, allowing the user to choose between several options.
     */
    public void showProductSearchMenu() throws IOException {
        // Initialize stop flag for menu loop
        boolean stopFlag = false;

        // Menu loop
        while (!stopFlag) {
            // Display menu options
            System.out.println(Color.BOLD + Color.UNDERLINE + "PRODUCT SEARCH MENU:" + Color.RESET +
                    Color.BOLD + Color.YELLOW + "\n[1]" + Color.RESET + " Search by name" +
                    Color.BOLD + Color.YELLOW + "\n[2]" + Color.RESET + " Search by price" +
                    Color.BOLD + Color.YELLOW + "\n[3]" + Color.RESET + " search by category" +
                    Color.BOLD + Color.YELLOW + "\n[4]" + Color.RESET + " Search by number of times purchased" +
                    Color.BOLD + Color.YELLOW + "\n[5]" + Color.RESET + " Return to the main menu");
            try {
                // Read user input from console
                System.out.print(Color.BOLD + Color.YELLOW + "> " + Color.RESET);
                int option = sc.nextInt();
                int optionSubmenu = 0;

                // Determine action based on user input
                switch (option) {
                    case 1:
                        controller.loadProductList();
                        do {
                            // Display submenu options
                            System.out.println(Color.BOLD + Color.UNDERLINE + "\tSEARCH METHOD:" + Color.RESET +
                                    Color.BOLD + Color.YELLOW + "\n\t[1]" + Color.RESET + " Exact search" +
                                    Color.BOLD + Color.YELLOW + "\n\t[2]" + Color.RESET + " Interval search");
                            System.out.print(Color.BOLD + Color.YELLOW + "> " + Color.RESET);
                            try {
                                optionSubmenu = sc.nextInt();
                                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                sc.nextLine(); // Consume newline character
                            } catch (InputMismatchException e) {
                                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                System.out.println(Color.BOLD + Color.YELLOW + "      INVALID INPUT: PLEASE ENTER AN INTEGER VALUE       " + Color.RESET);
                                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                sc.nextLine(); // Consume invalid input
                                continue;
                            }
                            switch (optionSubmenu) {
                                case 1:
                                    // Search by name
                                    System.out.print(Color.BOLD + Color.YELLOW + "Product name: " + Color.RESET);
                                    String nameProduct = sc.nextLine();
                                    System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                    System.out.print(controller.searchProduct(option, nameProduct));
                                    System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                    break;
                                case 2:
                                    // Search by name interval
                                    break;
                                default:
                                    System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                    System.out.println(Color.BOLD + Color.YELLOW + "                     OPTION NOT AVAILABLE                      " + Color.RESET);
                                    System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                    break;
                            }
                        } while (optionSubmenu != 1 && optionSubmenu != 2);
                        stopFlag = true;
                        break;
                    case 2:
                        controller.loadProductList();
                        do {
                            // Display submenu options
                            System.out.println(Color.BOLD + Color.UNDERLINE + "\tSEARCH METHOD:" + Color.RESET +
                                    Color.BOLD + Color.YELLOW + "\n\t[1]" + Color.RESET + " Exact search" +
                                    Color.BOLD + Color.YELLOW + "\n\t[2]" + Color.RESET + " Range search");
                            System.out.print(Color.BOLD + Color.YELLOW + "> " + Color.RESET);
                            try {
                                optionSubmenu = sc.nextInt();
                                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                sc.nextLine(); // Consume newline character
                            } catch (InputMismatchException e) {
                                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                System.out.println(Color.BOLD + Color.YELLOW + "      INVALID INPUT: PLEASE ENTER AN INTEGER VALUE       " + Color.RESET);
                                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                sc.nextLine(); // Consume invalid input
                                continue;
                            }
                            switch (optionSubmenu) {
                                case 1:
                                    // Search by price
                                    try {
                                        System.out.print(Color.BOLD + Color.YELLOW + "Price: " + Color.RESET);
                                        String price = sc.nextLine();
                                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                        System.out.print(controller.searchProduct(option, price));
                                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                    } catch (IncompleteDataException ex) {
                                            System.out.println(Color.BOLD + Color.YELLOW + ex.getMessage() + Color.RESET);
                                            System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                        }
                                    break;
                                case 2:
                                    // Search by price range
                                    break;
                                default:
                                    System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                    System.out.println(Color.BOLD + Color.YELLOW + "                     OPTION NOT AVAILABLE                      " + Color.RESET);
                                    System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                    break;
                            }
                        } while (optionSubmenu != 1 && optionSubmenu != 2);
                        stopFlag = true;
                        break;
                    case 3:
                        controller.loadProductList();
                        // Search by category
                        int categoryIndex = -1;
                        sc.nextLine();
                        do {
                            System.out.print(category);
                            System.out.print(Color.BOLD + Color.YELLOW + "Category: " + Color.RESET);
                            try {
                                categoryIndex = sc.nextInt();
                            } catch (NumberFormatException ex) {
                                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                System.out.println(Color.BOLD + Color.YELLOW + "      INVALID INPUT: PLEASE ENTER AN INTEGER VALUE       " + Color.RESET);
                                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                            }
                            if (categoryIndex < 1 || categoryIndex > 8) System.out.println(Color.BOLD + "Please enter a valid option" + Color.RESET);
                        } while (categoryIndex < 1 || categoryIndex > 8);
                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                        System.out.print(controller.searchProduct(option, String.valueOf(categoryIndex - 1)));
                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                        stopFlag = true;
                        break;
                    case 4:
                        controller.loadProductList();
                        do {
                            // Display submenu options
                            System.out.println(Color.BOLD + Color.UNDERLINE + "\tSEARCH METHOD:" + Color.RESET +
                                    Color.BOLD + Color.YELLOW + "\n\t[1]" + Color.RESET + " Exact search" +
                                    Color.BOLD + Color.YELLOW + "\n\t[2]" + Color.RESET + " Range search");
                            System.out.print(Color.BOLD + Color.YELLOW + "> " + Color.RESET);
                            try {
                                optionSubmenu = sc.nextInt();
                                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                sc.nextLine(); // Consume newline character
                            } catch (InputMismatchException e) {
                                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                System.out.println(Color.BOLD + Color.YELLOW + "      INVALID INPUT: PLEASE ENTER AN INTEGER VALUE       " + Color.RESET);
                                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                sc.nextLine(); // Consume invalid input
                                continue;
                            }
                            switch (optionSubmenu) {
                                case 1:
                                    // Search by number of times purchased
                                    try {
                                        System.out.print(Color.BOLD + Color.YELLOW + "Number of times purchased: " + Color.RESET);
                                        String purchasedNumber = sc.nextLine();
                                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                        System.out.print(controller.searchProduct(option, purchasedNumber));
                                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                    } catch (IncompleteDataException ex) {
                                        System.out.println(Color.BOLD + Color.YELLOW + ex.getMessage() + Color.RESET);
                                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                    }
                                    break;
                                case 2:
                                    // Search by number of times purchased range
                                    break;
                                default:
                                    System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                    System.out.println(Color.BOLD + Color.YELLOW + "                     OPTION NOT AVAILABLE                      " + Color.RESET);
                                    System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                    break;
                            }
                        } while (optionSubmenu != 1 && optionSubmenu != 2);
                        stopFlag = true;
                        break;
                    case 5:
                        // Return to the main menu
                        stopFlag = true;
                        break;
                    default:
                        // If user selects an option that is not available, display an error message
                        System.out.println(Color.BOLD + Color.YELLOW + "                     OPTION NOT AVAILABLE                      " + Color.RESET);
                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                        break;
                }
            } catch (InputMismatchException ex) {
                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                System.out.println(Color.BOLD + Color.YELLOW + "      INVALID INPUT: PLEASE ENTER AN INTEGER VALUE       " + Color.RESET);
                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                sc.nextLine(); // Consume the invalid input
            }
        }
    }

    /**
     * The method showOrderSearchMenu, displays the order search menu, allowing the user to choose between several options.
     */
    public void showOrderSearchMenu() throws IOException {
        // Initialize stop flag for menu loop
        boolean stopFlag = false;

        // Menu loop
        while (!stopFlag) {
            // Display menu options
            System.out.println(Color.BOLD + Color.UNDERLINE + "PRODUCT SEARCH MENU:" + Color.RESET +
                    Color.BOLD + Color.YELLOW + "\n[1]" + Color.RESET + " Search by buyer name" +
                    Color.BOLD + Color.YELLOW + "\n[2]" + Color.RESET + " Search by total price" +
                    Color.BOLD + Color.YELLOW + "\n[3]" + Color.RESET + " Search by purchased date" +
                    Color.BOLD + Color.YELLOW + "\n[4]" + Color.RESET + " Return to the main menu");
            try {
                // Read user input from console
                System.out.print(Color.BOLD + Color.YELLOW + "> " + Color.RESET);
                int option = sc.nextInt();
                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);

                // Determine action based on user input
                sc.nextLine();
                switch (option) {
                    case 1:
                        controller.loadOrderList();
                        System.out.println("Please enter the name of the buyer");
                        System.out.print(Color.BOLD + Color.YELLOW + "> " + Color.RESET);
                        String buyerName = sc.nextLine();
                        System.out.println(controller.searchOrder(option, buyerName));
                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                        stopFlag = true;
                        break;
                    case 2:
                        controller.loadOrderList();
                        System.out.println("Please enter the order total price");
                        System.out.print(Color.BOLD + Color.YELLOW + "> " + Color.RESET);
                        String totalPrice = sc.nextLine();
                        System.out.println(controller.searchOrder(option, totalPrice));
                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                        stopFlag = true;
                        break;
                    case 3:
                        controller.loadOrderList();
                        System.out.println("Please enter the purchased date");
                        System.out.print(Color.BOLD + Color.YELLOW + "> " + Color.RESET);
                        String purchasedDate = sc.nextLine();
                        System.out.println(controller.searchOrder(option, purchasedDate));
                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                        stopFlag = true;
                        break;
                    case 4:
                        // Return to the main menu
                        stopFlag = true;
                        break;
                    default:
                        // If user selects an option that is not available, display an error message
                        System.out.println(Color.BOLD + Color.YELLOW + "                     OPTION NOT AVAILABLE                      " + Color.RESET);
                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                        break;
                }
            } catch (InputMismatchException ex) {
                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                System.out.println(Color.BOLD + Color.YELLOW + "      INVALID INPUT: PLEASE ENTER AN INTEGER VALUE       " + Color.RESET);
                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                sc.nextLine(); // Consume the invalid input
            }
        }
    }

    private void addProduct() throws IOException {
        // Array contains
        String[] input = new String[6];
        // Array contains String product attributes
        String[] labels = {"Product name", "Description", "Price", "Quantity available", "Category", "Number of times purchased"};

        for (int i = 0; i < labels.length; i++) {
            // When is the turn of the category, shows the list of categories
            if (i == 4) {
                System.out.print(category);
            }
            System.out.print(Color.BOLD + Color.YELLOW + labels[i] + ": " + Color.RESET);
            input[i] = sc.nextLine();
            // If the category is not in the list of categories come back to show the list
            if (i == 4) {
                int categoryIndex = Integer.parseInt(input[i]);
                if (categoryIndex < 1 || categoryIndex > 8) {
                    System.out.println(Color.BOLD + "Please enter a valid option" + Color.RESET);
                    i--;
                } else {
                    input[i] = String.valueOf(categoryIndex - 1);
                }
            }
        }
        try {
            controller.addProduct(input);
        } catch (InputMismatchException | EmptyFileException | IncompleteDataException | NegativeNumberException ex) {
            System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
            System.out.println(Color.BOLD + Color.YELLOW + ex.getMessage() + Color.RESET);
            System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
        }

    }

    private void addOrder() throws IOException {
        controller.loadProductList();
        System.out.println("PRODUCTS LIST");
        controller.showProductList();
        // Array contains
        String[] input = new String[5];
        // Array contains String order attributes
        String[] labels = {"Buyer name", "Product list", "Product quantity", "Total price", "Date of purchase"};

        for (int i = 0; i < labels.length; i++) {
            // When is the turn of the product list, shows a note
            if (i == 1) {
                System.out.println(Color.BOLD + Color.YELLOW + "NOTE: " + Color.RESET + Color.ITALICS + "Type the List Product with the format NAME,NAME" + Color.RESET);
            } else if (i == 2) { // When is the turn of the quantity product, shows another note
                System.out.println(Color.BOLD + Color.YELLOW + "NOTE: " + Color.RESET + Color.ITALICS + "Type the Quantity of each corresponding product with the format NUM,NUM" + Color.RESET);
            } else if (i == 4) { // When is the turn of the date, shows another note
                System.out.println(Color.BOLD + Color.YELLOW + "NOTE: " + Color.RESET + Color.ITALICS + "Type the Purchase Date with the format YYYY-MM-DD" + Color.RESET);
            }
            System.out.print(Color.BOLD + Color.YELLOW + labels[i] + ": " + Color.RESET);
            input[i] = sc.nextLine();
        }
        try {
            controller.addOrder(input);
        } catch (IncompleteDataException | NegativeNumberException | DateFormatException ex) {
            System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
            System.out.println(Color.BOLD + Color.YELLOW + ex.getMessage() + Color.RESET);
            System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
        }
    }

    private void deleteProduct() throws IOException {
        controller.loadProductList();
        controller.showProductList();
        System.out.println("Please enter the name of the product to delete");
        String productName = sc.nextLine();
        controller.deleteProduct(productName);
    }

    private void deleteOrder() throws IOException {
        controller.loadOrderList();
        controller.showOrderList();
        System.out.println("Please enter the buyer name of the order to delete");
        String buyerName = sc.nextLine();
        System.out.println(controller.deleteOrder(buyerName));

    }

    public void changeQuantity() throws IOException {
        try {
            System.out.print(Color.BOLD + Color.YELLOW + "Product name: " + Color.RESET);
            String product = sc.nextLine();
            controller.showProductQuantity(product);
            System.out.print(Color.BOLD + Color.YELLOW + "Quantity to increase: " + Color.RESET);
            int quantity = sc.nextInt();
            controller.changeProductQuantity(product, quantity);
        } catch (IncompleteDataException | NegativeNumberException ex) {
            System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
            System.out.println(Color.BOLD + Color.YELLOW + ex.getMessage() + Color.RESET);
            System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
        }
    }

}