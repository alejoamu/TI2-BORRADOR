package model;

import color.Color;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.DateFormatException;
import exceptions.EmptyFileException;
import exceptions.IncompleteDataException;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class OrderList {

    static String folder = "dataBase";
    static String path = "dataBase/orders.json";
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

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        String data = gson.toJson(orders);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
        writer.write(data);
        writer.flush();
        fos.close();
    }

    public void load() throws IOException {
        File file = new File(path);
        try {
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                String json = new String(java.nio.file.Files.readAllBytes(file.toPath()));
                Gson gson = new Gson();
                Order[] array = gson.fromJson(json, Order[].class);
                orders.addAll(Arrays.asList(array));
                fis.close();
            } else {
                File f = new File(folder);
                if (!f.exists()) {
                    f.mkdirs();
                }
                file.createNewFile();
            }
        } catch (NullPointerException ex) {
            throw new EmptyFileException();
        }
    }

    public String searchOrder(int option, String data, int sortingType, int sortingVariable) {
        StringBuilder msg = new StringBuilder();
        if(data.isEmpty()) {throw new IncompleteDataException();}
        switch (option) {
            case 1:
                ArrayList<Order> ordersFoundBuyerName = searchOrderByBuyerName(data);
                sortingResults(ordersFoundBuyerName, sortingType, sortingVariable);
                for (Order o : ordersFoundBuyerName) {
                    msg.append(String.format("BuyerName: %s ProductsOrder: %s TotalPrice: %.2f Products Quantity: %s Purchase Date: %s ", o.getBuyerName(), Arrays.toString(o.getProductsOrder()), o.getTotalPrice(), Arrays.toString(o.getProductsQuantity()), o.getPurchasedDate())).append("\n");
                }
                break;
            case 2:
                double totalPrice = -1;
                try {
                    totalPrice = Double.parseDouble(data);
                } catch (NumberFormatException ex) {
                    throw new IncompleteDataException();
                }
                ArrayList<Order> ordersFoundTotalPrice = searchOrderByTotalPrice(totalPrice);
                sortingResults(ordersFoundTotalPrice, sortingType, sortingVariable);
                for (Order o : ordersFoundTotalPrice) {
                    msg.append(String.format("BuyerName: %s ProductsOrder: %s TotalPrice: %.2f Products Quantity: %s Purchase Date: %s ", o.getBuyerName(), Arrays.toString(o.getProductsOrder()), o.getTotalPrice(), Arrays.toString(o.getProductsQuantity()), o.getPurchasedDate())).append("\n");
                }
                break;
            case 3:
                LocalDate date;
                try {
                    date = LocalDate.parse(data);
                } catch (DateTimeException ex) {
                    throw new DateFormatException();
                }
                ArrayList<Order> ordersByDate = searchProductByPurchasedDate(date);
                sortingResults(ordersByDate, sortingType, sortingVariable);
                for (Order o : ordersByDate) {
                    msg.append(String.format("BuyerName: %s ProductsOrder: %s TotalPrice: %.2f Products Quantity: %s Purchase Date: %s ", o.getBuyerName(), Arrays.toString(o.getProductsOrder()), o.getTotalPrice(), Arrays.toString(o.getProductsQuantity()), o.getPurchasedDate())).append("\n");
                }
                break;
        }
        if (msg.length() == 0) {
            return Color.BOLD + Color.YELLOW + "               NO ORDER HAS THAT CHARACTERISTIC                \n" + Color.RESET;
        }
        return msg.toString();
    }

    public ArrayList<Order> searchOrderByBuyerName(String buyerName) {
        // Sort by buyer name ascending
        Comparator<Order> byBuyerName = (o1, o2) -> o1.getBuyerName().compareToIgnoreCase(o2.getBuyerName());
        orders.sort(byBuyerName);
        // Search for orders within the specified buyer name using binary search
        String products = "---";
        String[] arrProducts = products.split(",");
        String productsQuantity = "---";
        String[] arrProductsQuantity = productsQuantity.split(",");
        return binarySearch.searchObjectsByProperty(orders, byBuyerName, new Order(buyerName, arrProducts, arrProductsQuantity, Integer.MAX_VALUE, LocalDate.MAX));
    }

    public ArrayList<Order> searchOrderByTotalPrice(double totalPrice) {
        // Sort by price ascending
        Comparator<Order> byTotalPrice = (o1, o2) -> Double.compare(o1.getTotalPrice(), o2.getTotalPrice());
        orders.sort(byTotalPrice);
        // Search for orders with the specified price using binary search
        String products = "---";
        String[] arrProducts = products.split(",");
        String productsQuantity = "---";
        String[] arrProductsQuantity = productsQuantity.split(",");
        return binarySearch.searchObjectsByProperty(orders, byTotalPrice, new Order("---", arrProducts, arrProductsQuantity, totalPrice, LocalDate.MAX));
    }

    public ArrayList<Order> searchProductByPurchasedDate(LocalDate date) {
        // Sort by purchase date ascending
        Comparator<Order> byDate = (o1, o2) -> o1.getPurchasedDate().compareTo(o2.getPurchasedDate());
        orders.sort(byDate);
        // Search for orders with the specified date using binary search
        String products = "---";
        String[] arrProducts = products.split(",");
        String productsQuantity = "---";
        String[] arrProductsQuantity = productsQuantity.split(",");
        return binarySearch.searchObjectsByProperty(orders, byDate, new Order("---", arrProducts, arrProductsQuantity, Integer.MAX_VALUE, date));
    }

    public void sortingResults(ArrayList<Order> list, int sortingType, int sortingVariable) {
        switch (sortingVariable) {
            case 1:
                if (sortingType == 1) {
                    Comparator<Order> byBuyerName = (o1, o2) -> o1.getBuyerName().compareToIgnoreCase(o2.getBuyerName());
                    list.sort(byBuyerName);
                } else {
                    Comparator<Order> byBuyerNameDesc = (o1, o2) -> o2.getBuyerName().compareToIgnoreCase(o1.getBuyerName());
                    list.sort(byBuyerNameDesc);
                }
                break;
            case 2:
                if (sortingType == 1) {
                    Comparator<Order> byTotalPrice = (o1, o2) -> Double.compare(o1.getTotalPrice(), o2.getTotalPrice());
                    list.sort(byTotalPrice);
                } else {
                    Comparator<Order> byTotalPriceDesc = (o1, o2) -> Double.compare(o2.getTotalPrice(), o1.getTotalPrice());
                    list.sort(byTotalPriceDesc);
                }
                break;
            case 3:
                if (sortingType == 1) {
                    Comparator<Order> byDate = (o1, o2) -> o1.getPurchasedDate().compareTo(o2.getPurchasedDate());
                    list.sort(byDate);
                } else {
                    Comparator<Order> byDateDesc = (o1, o2) -> o2.getPurchasedDate().compareTo(o1.getPurchasedDate());
                    list.sort(byDateDesc);
                }
                break;
        }
    }

}
