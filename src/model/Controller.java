package model;

import exceptions.IncompleteDataException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

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
            throw new IncompleteDataException("Error: no se proporcionaron suficientes datos para agregar un producto. Asegúrate de ingresar los siguientes datos separados por '++': nombre, descripción, precio, cantidad en stock y número de veces comprado.");
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
            throw new IncompleteDataException("Error: no se proporcionaron suficientes datos para agregar un producto. Asegúrate de ingresar los siguientes datos separados por '++':");
        }

    }

    public void deleteProduct(String productName) throws IOException {
        productList.DeleteProduct(productName);
    }

    public String deleteOrder(String buyerName) throws IOException {
        return orderList.DeleteOrder(buyerName);
    }

    public void changeProductQuantity(String product, int newQuantity) throws IOException {
        productList.changeQuantity(product, newQuantity);
    }

    public void searchProduct(int option, String  data){
        productList.searchProduct(option, data);
    }
    public void searchOrder(int option, String data){
        orderList.searchOrder(option, data);
    }
}
