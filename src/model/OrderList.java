package model;

import com.google.gson.Gson;
import exceptions.EmptyFileException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class OrderList {
    static String folder = "dataBase";
    static String path = "dataBase/orders.txt";
    ArrayList<Order> orders;

    public OrderList() {
        orders = new ArrayList<>();
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
        writer.write(data); // hace que se guarde en el buffer
        writer.flush(); // hace que el buffer se limpie
        fos.close();
    }

    public void load() throws IOException {
        try {
            File file = new File(path);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                String content = "";
                String line = "";
                while ((line = reader.readLine()) != null) {
                    content += line + "\n";
                }
                //System.out.println(content);
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
        }catch (NullPointerException ex){
            throw new EmptyFileException("The file is empty");
        }
    }

    public void show() {
        try {
            for (Order order : orders) { //Order es cada elemento de la lista
                System.out.println("Buyer: " + order.getBuyerName() + " Products list: " + order.getProductsOrder() + " Total price: " + order.getTotalPrice() + " Purchase date: " + order.getPurchasedDate());
            }
        }catch (NullPointerException ex){
            throw new EmptyFileException("The file is empty");
        }

    }

    public void searchOrder(int option, String data) { //Busca la orden dentro del arrayList dependiendo del dato (aun no es busqueda binaria)
        if (option == 1) {
            for (int i = 0; i < orders.size(); i++) {
                if (orders.get(i).getBuyerName().equals(data)) {
                    System.out.println(orders.get(i).getBuyerName());
                    return;
                }
            }
        } else if (option == 2) {
            for (int i = 0; i < orders.size(); i++) {
                if (orders.get(i).getTotalPrice() == Double.parseDouble(data)) {
                    System.out.println(orders.get(i).getBuyerName());
                    return;
                }
            }
        } else if (option == 3) {
        }
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