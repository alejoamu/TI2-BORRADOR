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
            String[] arrProducts = data[1].split(",");
            String[] arrProductsQuantity = data[2].split(",");
            String[] arrDate = data[4].split("-");
            LocalDate date = LocalDate.of(Integer.parseInt(arrDate[0]), Integer.parseInt(arrDate[1]), Integer.parseInt(arrDate[2]));

            Order newOrder = new Order(data[0], arrProducts, arrProductsQuantity, Integer.parseInt(data[3]), date);
            if (orderList.searchOrderByBuyerName(newOrder.getBuyerName()) != null) {
                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                System.out.println(Color.BOLD + Color.YELLOW + "                ORDER WAS ALREADY REGISTERED                 " + Color.RESET);
                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
            } else {
                if (arrProducts.length != arrProductsQuantity.length) {
                    if (arrProducts.length > arrProductsQuantity.length) {
                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                        System.out.println(Color.BOLD + Color.YELLOW + "                A PRODUCT IN THE ORDER DOES NOT HAVE A PRICE                  " + Color.RESET);
                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                    } else if (arrProducts.length < arrProductsQuantity.length) {
                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                        System.out.println(Color.BOLD + Color.YELLOW + "                THERE IS A PRICE FOR A NON-EXISTENT PRODUCT                  " + Color.RESET);
                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                    }
                    return;
                }
                for (int i = 0; i < arrProducts.length; i++) {
                    if (productList.searchProductByName(arrProducts[i]) == null) {
                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                        System.out.println(Color.BOLD + Color.YELLOW + "                A PRODUCT IN THE ORDER DOES NOT EXIST                  " + Color.RESET);
                        System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                        return;
                    }
                }
                //Decreases the available quantity of the product
                for (int j = 0; j < arrProducts.length; j++) {
                    Product product = productList.searchProductByName(arrProducts[j]);
                    for (int k = 0; k < arrProductsQuantity.length; k++) {
                        if (product.getQuantityAvailable() >= Integer.parseInt(arrProductsQuantity[k])) {
                            product.subtractQuantityAvailable(Integer.parseInt(arrProductsQuantity[k]));
                        } else {
                            System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                            System.out.println(Color.BOLD + Color.YELLOW + "        NOT ENOUGH QUANTITIES AVAILABLE FOR ONE PRODUCT                   " + Color.RESET);
                            System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                            return;
                        }
                    }
                }
                orderList.getOrders().add(newOrder);
                orderList.save();
                productList.save();
                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
                System.out.println(Color.BOLD + Color.YELLOW + "                  ORDER SUCCESSFULLY ADDED                   " + Color.RESET);
                System.out.println(Color.BLUE + "***************************************************************" + Color.RESET);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new IncompleteDataException();
        } catch (NumberFormatException ex) {
            throw new DateFormatException();
        }
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
