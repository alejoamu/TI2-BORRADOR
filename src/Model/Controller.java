package Model;

import java.io.IOException;
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
        String[] data = input.split("\\+\\+");
        System.out.println(Arrays.toString(data));
        productList.getProducts().add(
                new Product(data[0], data[1], Double.parseDouble(data[2]), Integer.parseInt(data[3]), category, Integer.parseInt(data[4]))
        );
        productList.save();
    }

    public void addOrder(String input) throws IOException {
        String[] dataOrders = input.split("\\+\\+");
        System.out.println(Arrays.toString(dataOrders));
        orderList.getOrders().add(
                new Order(dataOrders[0], dataOrders[1], Double.parseDouble(dataOrders[2]))
        );
        orderList.save();
    }

    public void deleteProduct(String productName) throws IOException {
        productList.DeleteProduct(productName);
    }

    public void deleteOrder(String buyerName) throws IOException {
        orderList.DeleteOrder(buyerName);
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
