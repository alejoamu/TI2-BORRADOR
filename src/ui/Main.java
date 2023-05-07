package ui;

import color.Color;
import exceptions.*;
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
        controller.loadProductList();
        controller.loadOrderList();
        manager.showMainMenu();
    }

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
                sc.nextLine();
            }
        }
    }

    public void showProductManagementMenu() throws IOException {
        // Initialize stop flag for menu loop
        boolean stopFlag = false;

        // Menu loop
        while (!stopFlag) {
            // Display menu options
            System.out.println(Color.BOLD + Color.UNDERLINE + "PRODUCT MANAGEMENT MENU:" + Color.RESET +
                    Color.BOLD + Color.YELLOW + "\n[1]" + Color.RESET + " Add new product" +
                    Color.BOLD + Color.YELLOW + "\n[2]" + Color.RESET + " Increase the available quantity of a product" +
                    Color.BOLD + Color.YELLOW + "\n[3]" + Color.RESET + " Search product" +
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
                        // Add new product
                        addProduct();
                        stopFlag = true;
                        break;
                    case 2:
                        // Increase quantity
                        changeQuantity();
                        stopFlag = true;
                        break;
                    case 3:
                        // Search product
                        showProductSearchMenu();
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
                sc.nextLine();
            }
        }
    }

    public void showOrderManagementMenu() throws IOException {
        // Initialize stop flag for menu loop
        boolean stopFlag = false;

        // Menu loop
        while (!stopFlag) {
            // Display menu options
            System.out.println(Color.BOLD + Color.UNDERLINE + "ORDER MANAGEMENT MENU:" + Color.RESET +
                    Color.BOLD + Color.YELLOW + "\n[1]" + Color.RESET + " Add new order" +
                    Color.BOLD + Color.YELLOW + "\n[2]" + Color.RESET + " Search order" +
                    Color.BOLD + Color.YELLOW + "\n[3]" + Color.RESET + " Return to the main menu");
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
                        addOrder();
                        stopFlag = true;
                        break;
                    case 2:
                        // Search order
                        showOrderSearchMenu();
                        stopFlag = true;
                        break;
                    case 3:
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
                sc.nextLine();
            }
        }
    }

    public void showProductSearchMenu() {
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
                    Color.BOLD + Color.YELLOW + "\n[5]" + Color.RESET + " Search by quantity available" +
                    Color.BOLD + Color.YELLOW + "\n[6]" + Color.RESET + " Return to the main menu");
            try {
                // Read user input from console
                System.out.print(Color.BOLD + Color.YELLOW + "> " + Color.RESET);
                int option = sc.nextInt();
                int optionSubmenu = 0;
                int[] sort;

                // Determine action based on user input
                switch (option) {
                    case 1:
                        do {
                            // Display submenu options
                            System.out.println(Color.BOLD + Color.UNDERLINE + "\tSEARCH METHOD:" + Color.RESET +
                                    Color.BOLD + Color.YELLOW + "\n\t[1]" + Color.RESET + " Exact search" +
                                    Color.BOLD + Color.YELLOW + "\n\t[2]" + Color.RESET + " Interval search");
                            System.out.print(Color.BOLD + Color.YELLOW + "> " + Color.RESET);
                            optionSubmenu = sc.nextInt();
                            System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                            sc.nextLine();

                            switch (optionSubmenu) {
                                case 1:
                                    // Search by name
                                    System.out.print(Color.BOLD + Color.YELLOW + "Product name: " + Color.RESET);
                                    String nameProduct = sc.nextLine();
                                    System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                    System.out.print(controller.searchProduct(option, nameProduct, 0, 0));
                                    System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                    break;
                                case 2:
                                    // Search by name interval
                                    System.out.print(Color.BOLD + Color.YELLOW + "Initial prefix: " + Color.RESET);
                                    String initialPrefix = sc.nextLine();
                                    System.out.print(Color.BOLD + Color.YELLOW + "Final prefix: " + Color.RESET);
                                    String finalPrefix = sc.nextLine();
                                    sort = sortingSubmenuProduct();
                                    System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                    System.out.print(controller.searchProduct(option, initialPrefix, finalPrefix, sort[0], sort[1]));
                                    System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                    break;
                                default:
                                    System.out.println(Color.BOLD + Color.YELLOW + "                     OPTION NOT AVAILABLE                      " + Color.RESET);
                                    System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                    break;
                            }
                        } while (optionSubmenu != 1 && optionSubmenu != 2);
                        stopFlag = true;
                        break;
                    case 2:
                        do {
                            // Display submenu options
                            System.out.println(Color.BOLD + Color.UNDERLINE + "\tSEARCH METHOD:" + Color.RESET +
                                    Color.BOLD + Color.YELLOW + "\n\t[1]" + Color.RESET + " Exact search" +
                                    Color.BOLD + Color.YELLOW + "\n\t[2]" + Color.RESET + " Range search");
                            System.out.print(Color.BOLD + Color.YELLOW + "> " + Color.RESET);
                            optionSubmenu = sc.nextInt();
                            System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                            sc.nextLine();

                            switch (optionSubmenu) {
                                case 1:
                                    // Search by price
                                    try {
                                        System.out.print(Color.BOLD + Color.YELLOW + "Price: " + Color.RESET);
                                        String price = sc.nextLine();
                                        sort = sortingSubmenuProduct();
                                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                        System.out.print(controller.searchProduct(option, price, sort[0], sort[1]));
                                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                    } catch (IncompleteDataException ex) {
                                        System.out.println(Color.BOLD + Color.YELLOW + ex.getMessage() + Color.RESET);
                                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                    }
                                    break;
                                case 2:
                                    // Search by price range
                                    try {
                                        System.out.print(Color.BOLD + Color.YELLOW + "Minimal price: " + Color.RESET);
                                        String minPrice = sc.nextLine();
                                        System.out.print(Color.BOLD + Color.YELLOW + "Maximum price: " + Color.RESET);
                                        String maxPrice = sc.nextLine();
                                        sort = sortingSubmenuProduct();
                                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                        System.out.print(controller.searchProduct(option, minPrice, maxPrice, sort[0], sort[1]));
                                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                    } catch (IncompleteDataException ex) {
                                        System.out.println(Color.BOLD + Color.YELLOW + ex.getMessage() + Color.RESET);
                                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                    }
                                    break;
                                default:
                                    System.out.println(Color.BOLD + Color.YELLOW + "                     OPTION NOT AVAILABLE                      " + Color.RESET);
                                    System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                    break;
                            }
                        } while (optionSubmenu != 1 && optionSubmenu != 2);
                        stopFlag = true;
                        break;
                    case 3:
                        // Search by category
                        int categoryIndex = -1;
                        sc.nextLine();
                        do {
                            System.out.print(category);
                            System.out.print(Color.BOLD + Color.YELLOW + "Category: " + Color.RESET);
                            categoryIndex = sc.nextInt();
                            if (categoryIndex < 1 || categoryIndex > 8) {
                                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                System.out.println(Color.BOLD + Color.YELLOW + "                     OPTION NOT AVAILABLE                      " + Color.RESET);
                                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                            }
                        } while (categoryIndex < 1 || categoryIndex > 8);
                        sc.nextLine();
                        sort = sortingSubmenuProduct();
                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                        System.out.print(controller.searchProduct(option, String.valueOf(categoryIndex - 1), sort[0], sort[1]));
                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                        stopFlag = true;
                        break;
                    case 4:
                        do {
                            // Display submenu options
                            System.out.println(Color.BOLD + Color.UNDERLINE + "\tSEARCH METHOD:" + Color.RESET +
                                    Color.BOLD + Color.YELLOW + "\n\t[1]" + Color.RESET + " Exact search" +
                                    Color.BOLD + Color.YELLOW + "\n\t[2]" + Color.RESET + " Range search");
                            System.out.print(Color.BOLD + Color.YELLOW + "> " + Color.RESET);
                            optionSubmenu = sc.nextInt();
                            System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                            sc.nextLine();

                            switch (optionSubmenu) {
                                case 1:
                                    // Search by number of times purchased
                                    try {
                                        System.out.print(Color.BOLD + Color.YELLOW + "Number of times purchased: " + Color.RESET);
                                        String purchasedNumber = sc.nextLine();
                                        sort = sortingSubmenuProduct();
                                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                        System.out.print(controller.searchProduct(option, purchasedNumber, sort[0], sort[1]));
                                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                    } catch (IncompleteDataException ex) {
                                        System.out.println(Color.BOLD + Color.YELLOW + ex.getMessage() + Color.RESET);
                                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                    }
                                    break;
                                case 2:
                                    // Search by number of times purchased range
                                    try {
                                        System.out.print(Color.BOLD + Color.YELLOW + "Minimal purchased number: " + Color.RESET);
                                        String minPurchasedNumber = sc.nextLine();
                                        System.out.print(Color.BOLD + Color.YELLOW + "Maximum purchased number: " + Color.RESET);
                                        String maxPurchasedNumber = sc.nextLine();
                                        sort = sortingSubmenuProduct();
                                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                        System.out.print(controller.searchProduct(option, minPurchasedNumber, maxPurchasedNumber, sort[0], sort[1]));
                                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                    } catch (IncompleteDataException ex) {
                                        System.out.println(Color.BOLD + Color.YELLOW + ex.getMessage() + Color.RESET);
                                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                    }
                                    break;
                                default:
                                    System.out.println(Color.BOLD + Color.YELLOW + "                     OPTION NOT AVAILABLE                      " + Color.RESET);
                                    System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                                    break;
                            }
                        } while (optionSubmenu != 1 && optionSubmenu != 2);
                        stopFlag = true;
                        break;
                    case 5:
                        // Search by quantity available range
                        try {
                            sc.nextLine();
                            System.out.print(Color.BOLD + Color.YELLOW + "Minimal quantity available: " + Color.RESET);
                            String minQuantityAvailable = sc.nextLine();
                            System.out.print(Color.BOLD + Color.YELLOW + "Maximum quantity available: " + Color.RESET);
                            String maxQuantityAvailable = sc.nextLine();
                            sort = sortingSubmenuProduct();
                            System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                            System.out.print(controller.searchProduct(option, minQuantityAvailable, maxQuantityAvailable, sort[0], sort[1]));
                            System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                        } catch (IncompleteDataException ex) {
                            System.out.println(Color.BOLD + Color.YELLOW + ex.getMessage() + Color.RESET);
                            System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                        }
                        stopFlag = true;
                        break;
                    case 6:
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
                sc.nextLine();
            }
        }
    }

    public void showOrderSearchMenu() {
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
                int[] sort;
                switch (option) {
                    case 1:
                        // Search by name
                        System.out.print(Color.BOLD + Color.YELLOW + "Buyer name: " + Color.RESET);
                        String buyerName = sc.nextLine();
                        sort = sortingSubmenuOrder();
                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                        System.out.print(controller.searchOrder(option, buyerName, sort[0], sort[1]));
                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                        stopFlag = true;
                        break;
                    case 2:
                        System.out.print(Color.BOLD + Color.YELLOW + "Total price: " + Color.RESET);
                        String totalPrice = sc.nextLine();
                        sort = sortingSubmenuOrder();
                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                        System.out.print(controller.searchOrder(option, totalPrice, sort[0], sort[1]));
                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                        stopFlag = true;
                        break;
                    case 3:
                        System.out.print(Color.BOLD + Color.YELLOW + "Purchased date (YYYY-MM-DD): " + Color.RESET);
                        String purchasedDate = sc.nextLine();
                        sort = sortingSubmenuOrder();
                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                        System.out.print(controller.searchOrder(option, purchasedDate, sort[0], sort[1]));
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
                sc.nextLine();
            }
        }
    }

    public int[] sortingSubmenuProduct() {
        // Array results
        int[] result = new int[2];
        try {
            int sortType = -1;
            do {
                System.out.println(Color.BOLD + Color.UNDERLINE + "\tSORT TYPE METHOD:" + Color.RESET +
                        Color.BOLD + Color.YELLOW + "\n\t[1]" + Color.RESET + " Upward" +
                        Color.BOLD + Color.YELLOW + "\n\t[2]" + Color.RESET + " Falling");
                System.out.print(Color.BOLD + Color.YELLOW + "> " + Color.RESET);
                sortType = Integer.parseInt(sc.nextLine());
            } while (sortType < 1 || sortType > 2);
            result[0] = sortType;

            // Array contains String product attributes
            String[] labels = {Color.BOLD + Color.UNDERLINE + "\tSORT VARIABLE" + Color.RESET,
                    Color.BOLD + Color.YELLOW + "\n\t[1] " + Color.RESET + "Product name",
                    Color.BOLD + Color.YELLOW + "\n\t[2] " + Color.RESET + "Price",
                    Color.BOLD + Color.YELLOW + "\n\t[3] " + Color.RESET + "Quantity available",
                    Color.BOLD + Color.YELLOW + "\n\t[4] " + Color.RESET + "Category",
                    Color.BOLD + Color.YELLOW + "\n\t[5] " + Color.RESET + "Number of times purchased\n"};
            int sortVariable = -1;
            do {
                for (String label : labels) {
                    System.out.print(label);
                }
                System.out.print(Color.BOLD + Color.YELLOW + "> " + Color.RESET);
                sortVariable = Integer.parseInt(sc.nextLine());
            } while (sortVariable < 1 || sortVariable > 5);
            result[1] = sortVariable;
        } catch (IncompleteDataException | NumberFormatException  ignored) {
        }
        return result;
    }

    public int[] sortingSubmenuOrder() {
        // Array results
        int[] result = new int[2];
        try {
            int sortType = -1;
            do {
                System.out.println(Color.BOLD + Color.UNDERLINE + "\tSORT TYPE METHOD:" + Color.RESET +
                        Color.BOLD + Color.YELLOW + "\n\t[1]" + Color.RESET + " Upward" +
                        Color.BOLD + Color.YELLOW + "\n\t[2]" + Color.RESET + " Falling");
                System.out.print(Color.BOLD + Color.YELLOW + "> " + Color.RESET);
                sortType = Integer.parseInt(sc.nextLine());
            } while (sortType < 1 || sortType > 2);
            result[0] = sortType;

            // Array contains String product attributes
            String[] labels = {Color.BOLD + Color.UNDERLINE + "\tSORT VARIABLE" + Color.RESET,
                    Color.BOLD + Color.YELLOW + "\n\t[1] " + Color.RESET + "Buyer name",
                    Color.BOLD + Color.YELLOW + "\n\t[2] " + Color.RESET + "Total price",
                    Color.BOLD + Color.YELLOW + "\n\t[3] " + Color.RESET + "Date of purchase\n"};
            int sortVariable = -1;
            do {
                for (String label : labels) {
                    System.out.print(label);
                }
                System.out.print(Color.BOLD + Color.YELLOW + "> " + Color.RESET);
                sortVariable = Integer.parseInt(sc.nextLine());
            } while (sortVariable < 1 || sortVariable > 3);
            result[1] = sortVariable;
        } catch (IncompleteDataException | NumberFormatException  ignored) {
        }
        return result;
    }

    public void addProduct() throws IOException {
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

    public void addOrder() throws IOException {
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
        } catch (IncompleteDataException | NegativeNumberException | DateFormatException | DecimalNumberException ex) {
            System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
            System.out.println(Color.BOLD + Color.YELLOW + ex.getMessage() + Color.RESET);
            System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
        }
    }

    public void changeQuantity() throws IOException {
        try {
            System.out.print(Color.BOLD + Color.YELLOW + "Product name: " + Color.RESET);
            String product = sc.nextLine();
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