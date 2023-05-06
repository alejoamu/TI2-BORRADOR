package model;

import com.google.gson.Gson;
import com.sun.org.apache.xpath.internal.operations.Or;
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

    public ArrayList<Order> searchOrderByTotalPrice(double price) {
        // Sort by price ascending
        Comparator<Order> byPrice = (p1, p2) -> Double.compare(p1.getTotalPrice(), p2.getTotalPrice());
        orders.sort(byPrice);
        // Search for orders with the specified price using binary search
        ArrayList<Order> result = new ArrayList<>();
        String products = "---";
        String[] arrProducts = products.split(",");
        String productsQuantity = "---";
        String[] arrProductsQuantity = productsQuantity.split(",");

        int index = binarySearch.search(orders, byPrice, new Order("---", arrProducts, arrProductsQuantity, Integer.MAX_VALUE, LocalDate.MAX), 0, orders.size() - 1);
        if (index != -1) {
            // Add the order at the found index to the result list
            result.add(orders.get(index));
            // Search for any other orders with the same price that appear before the found index
            int leftIndex = index - 1;
            while (leftIndex >= 0 && byPrice.compare(orders.get(leftIndex), result.get(0)) == 0) {
                result.add(0, orders.get(leftIndex));
                leftIndex--;
            }
            // Search for any other orders with the same price that appear after the found index
            int rightIndex = index + 1;
            while (rightIndex < orders.size() && byPrice.compare(orders.get(rightIndex), result.get(result.size() - 1)) == 0) {
                result.add(orders.get(rightIndex));
                rightIndex++;
            }
        }
        return result;
    }

    public ArrayList<Order> searchProductByPurchasedDate(LocalDate date) {
        // Sort by purchase date ascending
        Comparator<Order> byDate = (p1, p2) -> p1.getPurchasedDate().compareTo(p2.getPurchasedDate());
        orders.sort(byDate);
        // Search for orders with the specified category using binary search
        ArrayList<Order> result = new ArrayList<>();
        String products = "---";
        String[] arrProducts = products.split(",");
        String productsQuantity = "---";
        String[] arrProductsQuantity = productsQuantity.split(",");
        int index = binarySearch.search(orders, byDate, new Order("---", arrProducts, arrProductsQuantity, Integer.MAX_VALUE, date), 0, orders.size() - 1);
        if (index != -1) {
            // Add the order at the found index to the result list
            result.add(orders.get(index));
            // Search for any other orders with the same purchase date that appear before the found index
            int leftIndex = index - 1;
            while (leftIndex >= 0 && byDate.compare(orders.get(leftIndex), result.get(0)) == 0) {
                result.add(0, orders.get(leftIndex));
                leftIndex--;
            }
            // Search for any other orders with the same purchase date that appear after the found index
            int rightIndex = index + 1;
            while (rightIndex < orders.size() && byDate.compare(orders.get(rightIndex), result.get(result.size() - 1)) == 0) {
                result.add(orders.get(rightIndex));
                rightIndex++;
            }
        }
        return result;
    }

    /*public ArrayList<Order> searchProductByProductsOrder(String products) {
        // Sort by products order ascending
        Comparator<Order> byProductsOrder = (p1, p2) -> p1.getProductsOrder().compareTo(p2.getProductsOrder());/// arreglar esto
        orders.sort(byProductsOrder);
        // Search for products with the specified products order using binary search
        ArrayList<Order> result = new ArrayList<>();
        String[] arrProducts = products.split(",");
        String productsQuantity = "---";
        String[] arrProductsQuantity = productsQuantity.split(",");
        int index = binarySearch.search(orders, byProductsOrder, new Order("---", arrProducts, arrProductsQuantity, Integer.MAX_VALUE, LocalDate.MAX), 0, orders.size() - 1);
        if (index != -1) {
            // Add the order at the found index to the result list
            result.add(orders.get(index));
            // Search for any other orders with the same products order that appear before the found index
            int leftIndex = index - 1;
            while (leftIndex >= 0 && byProductsOrder.compare(orders.get(leftIndex), result.get(0)) == 0) {
                result.add(0, orders.get(leftIndex));
                leftIndex--;
            }
            // Search for any other orders with the same products order that appear after the found index
            int rightIndex = index + 1;
            while (rightIndex < orders.size() && byProductsOrder.compare(orders.get(rightIndex), result.get(result.size() - 1)) == 0) {
                result.add(orders.get(rightIndex));
                rightIndex++;
            }
        }
        return result;
    }*/
}
