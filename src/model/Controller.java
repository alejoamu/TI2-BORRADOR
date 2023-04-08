package model;

import exceptions.DateFormatException;
import exceptions.IncompleteDataException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

public class Controller {
    static OrderList orderList = new OrderList();
    static ProductList productList = new ProductList();

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

    public void showProductQuantity(String product) {
        productList.showQuantity(product);
    }

    public void addProduct(String input, Category category) throws IOException {
        try{
            String[] data = input.split("\\+\\+");
            System.out.println(Arrays.toString(data));
            productList.getProducts().add(
                    new Product(data[0], data[1], Double.parseDouble(data[2]), Integer.parseInt(data[3]), category, Integer.parseInt(data[4]))
            );
            productList.save();
        } catch (ArrayIndexOutOfBoundsException ex){
            throw new IncompleteDataException();
        }

    }

    public void addOrder(String input) throws IOException {
        try {
            String[] dataOrders = input.split("\\+\\+");
            System.out.println(Arrays.toString(dataOrders));
            String[] arrDate = dataOrders[3].split("-");
            LocalDate date =LocalDate.of(Integer.parseInt(arrDate[0]), Integer.parseInt(arrDate[1]), Integer.parseInt(arrDate[2]));
            orderList.getOrders().add(
                    new Order(dataOrders[0], dataOrders[1], Double.parseDouble(dataOrders[2]), date)
            );
            orderList.save();
        }catch (ArrayIndexOutOfBoundsException ex){
            throw new IncompleteDataException();
        }catch (NumberFormatException ex){
            throw new DateFormatException();
        }

    }

    public void deleteProduct(String productName) throws IOException {
        productList.DeleteProduct(productName);
    }

    public String deleteOrder(String buyerName) throws IOException {
        return orderList.deleteOrder(buyerName);
    }

    public void changeProductQuantity(String product, int newQuantity) throws IOException {
        productList.changeQuantity(product, newQuantity);
    }

    public String searchProduct(int option, String  data){
        return productList.searchProduct(option, data);
    }
    public String searchOrder(int option, String data){
        return orderList.searchOrder(option, data);
    }
}
