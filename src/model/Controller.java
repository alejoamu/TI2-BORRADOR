package model;

import color.Color;
import exceptions.DateFormatException;
import exceptions.IncompleteDataException;
import exceptions.NegativeNumberException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

public class Controller {
    private OrderList orderList = new OrderList();
    private ProductList productList = new ProductList();

    public void loadProductList() throws IOException {
        productList.load();
    }

    public void loadOrderList() throws IOException {
        orderList.load();
    }

    public void showProductList() throws IOException {
        productList.show();
    }

    public void showOrderList() throws IOException {
        orderList.show();
    }

    public String getCategory() {
        StringBuilder msg = new StringBuilder();
        Category[] categories = Category.values();
        for (int i = 0; i < categories.length; i++) {
            String categoryName = categories[i].toString().replace("_", " ");
            msg.append(Color.BOLD + Color.YELLOW + "\t[").append(i + 1).append("]" + Color.RESET).append(" ").append(categoryName).append("\n");
        }
        return msg.toString();
    }

    public void addProduct(String[] data) throws IOException {
        try {
            Product newProduct = new Product(data[0], data[1], Double.parseDouble(data[2]), Integer.parseInt(data[3]), Category.values()[Integer.parseInt(data[4])], Integer.parseInt(data[5]));
            if (productList.searchProductByName(newProduct.getProductName()) != null) {
                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                System.out.println(Color.BOLD + Color.YELLOW + "                PRODUCT WAS ALREADY REGISTERED                 " + Color.RESET);
                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
            } else {
                productList.getProducts().add(newProduct);
                productList.save();
                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                System.out.println(Color.BOLD + Color.YELLOW + "                  PRODUCT SUCCESSFULLY ADDED                   " + Color.RESET);
                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new IncompleteDataException();
        }
    }

    public void changeProductQuantity(String product, int quantity) throws IOException {
        if (productList.changeQuantity(product, quantity)) {
            productList.save();
            System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
            System.out.println(Color.BOLD + Color.YELLOW + "                  UPDATED AVAILABLE QUANTITY                   " + Color.RESET);
            System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
        } else {
            System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
            System.out.println(Color.BOLD + Color.YELLOW + "                   PRODUCT IS NOT REGISTERED                   " + Color.RESET);
            System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
        }
    }

    public void addOrder(String[] data) throws IOException {
        try {
            System.out.println(Arrays.toString(data));
            String[] arrDate = data[4].split("-");
            LocalDate date = LocalDate.of(Integer.parseInt(arrDate[0]), Integer.parseInt(arrDate[1]), Integer.parseInt(arrDate[2]));
            orderList.getOrders().add(
                    new Order(data[0], data[1], Double.parseDouble(data[2]), date)
            );
            orderList.save();
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new IncompleteDataException();
        } catch (NumberFormatException ex) {
            throw new DateFormatException();
        }
    }

    public String deleteOrder(String buyerName) throws IOException {
        return orderList.deleteOrder(buyerName);
    }

    public String searchProduct(int option, String data, int sortingType, int sortingVariable) {
        return productList.searchProduct(option, data, sortingType, sortingVariable);
    }

    public String searchProduct(int option, String minData, String maxData, int sortingType, int sortingVariable) {
        return productList.searchProduct(option, minData, maxData, sortingType, sortingVariable);
    }

    public String searchOrder(int option, String data) {
        return orderList.searchOrder(option, data);
    }

    public OrderList getOrderList() {
        return orderList;
    }

    public ProductList getProductList() {
        return productList;
    }
}
