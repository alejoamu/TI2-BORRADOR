package model;

import com.google.gson.Gson;
import exceptions.EmptyFileException;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class OrderList {
    static String folder = "dataBase";
    static String path = "dataBase/orders.txt";
    private ArrayList<Order> orders;
    private BinarySearch<Order> binarySearch;

    public OrderList() {
        orders = new ArrayList<>();
        binarySearch = new BinarySearch<>();
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public void save() throws IOException {
        File file = new File(path);
        FileOutputStream fos = new FileOutputStream(file);

        Gson gson = new Gson();
        String data = gson.toJson(orders);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
        writer.write(data);
        writer.flush();
        fos.close();
    }

    public void load() throws IOException {
//        try {
        File file = new File(path);
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String content = "";
            String line = "";
            while ((line = reader.readLine()) != null) {
                content += line + "\n";
            }
            Gson gson = new Gson();
            Order[] array = gson.fromJson(content, Order[].class);
            orders.addAll(Arrays.asList(array));
            fis.close();
        } else {
            File f = new File(folder);
            if (!f.exists()) {
                f.mkdirs();
            }
            file.createNewFile();
        }
//        }catch (NullPointerException ex){
//            throw new EmptyFileException();
//        }

    }

    public Order searchOrderByBuyerName(String buyerName) {
        // Ordenar por nombre ascendente
        Comparator<Order> byName = (o1, o2) -> o1.getBuyerName().compareToIgnoreCase(o2.getBuyerName());
        orders.sort(byName);
        // Buscar la orden por el nombre del comprador
        String products = "---";
        String[] arrProducts = products.split(",");
        String productsQuantity = "---";
        String[] arrProductsQuantity = productsQuantity.split(",");

        int index = binarySearch.search(orders, byName, new Order(buyerName, arrProducts, arrProductsQuantity, Integer.MAX_VALUE, LocalDate.MAX), 0, orders.size() - 1);
        if (index == -1) return null;
        else return orders.get(index);
    }

    public void show() {
        try {
            for (Order order : orders) { //Order es cada elemento de la lista
                System.out.println("Buyer: " + order.getBuyerName() + " Products list: " + order.getProductsOrder() + " Total price: " + order.getTotalPrice() + " Purchase date: " + order.getPurchasedDate());
            }
        } catch (NullPointerException ex) {
            throw new EmptyFileException();
        }

    }

    public String searchOrder(int option, String data) { //Busca la orden dentro del arrayList dependiendo del dato (aun no es busqueda binaria)
        String msg = "the order doesn't exist in the list";
        if (option == 1) {
            for (int i = 0; i < orders.size(); i++) {
                if (orders.get(i).getBuyerName().equals(data)) {
                    msg = "Buyer: " + orders.get(i).getBuyerName() + " Products list: " + orders.get(i).getProductsOrder() + " Total price: " + orders.get(i).getTotalPrice() + " Purchase date: " + orders.get(i).getPurchasedDate();
                    return msg;
                }
            }
        } else if (option == 2) {
            for (int i = 0; i < orders.size(); i++) {
                if (orders.get(i).getTotalPrice() == Double.parseDouble(data)) {
                    msg = "Buyer: " + orders.get(i).getBuyerName() + " Products list: " + orders.get(i).getProductsOrder() + " Total price: " + orders.get(i).getTotalPrice() + " Purchase date: " + orders.get(i).getPurchasedDate();
                    return msg;
                }
            }
        } else if (option == 3) {

        }
        return msg;
    }

    public String deleteOrder(String buyName) throws IOException { //Eliminar la orden
        String msg = "The order doesn't exist in the list";
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getBuyerName().equals(buyName)) {
                orders.set(i, null);
                save();
                msg = "Order deleted successfully";
            }
        }
        return msg;
    }
}
