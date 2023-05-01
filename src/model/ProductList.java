package model;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class ProductList {

    static String folder = "dataBase";
    static String path = "dataBase/products.txt";

    private ArrayList<Product> products;
    private BinarySearch<Product> binarySearch;

    public ProductList() {
        products = new ArrayList<>();
        binarySearch = new BinarySearch<>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void save() throws IOException {
        File file = new File(path);
        FileOutputStream fos = new FileOutputStream(file);

        Gson gson = new Gson();
        String data = gson.toJson(products);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
        writer.write(data);
        writer.flush();
        fos.close();
    }

    public void load() throws IOException {
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
            Product[] array = gson.fromJson(content, Product[].class);
            products.addAll(Arrays.asList(array));
            fis.close();
        } else {
            File f = new File(folder);
            if (!f.exists()) {
                f.mkdirs();
            }
            file.createNewFile();
        }
    }

    public Product searchProductByName(String nameProduct) {
        // Ordenar por nombre ascendente
        Comparator<Product> byName = (p1, p2) -> p1.getProductName().compareToIgnoreCase(p2.getProductName());
        products.sort(byName);
        // Buscar el producto por su nombre
        int index = binarySearch.search(products, byName, new Product(nameProduct, "---", Integer.MAX_VALUE, Integer.MAX_VALUE, Category.BOOKS, Integer.MAX_VALUE), 0, products.size() - 1);
        if (index == -1) return null;
        else return products.get(index);
    }

    public void show() {
        for (Product p : products) { //P es cada elemento de la lista
            System.out.println(p.getProductName());
            return;
        }
    }

    public String searchProduct(int option, String data) { //Busca el producto dentro del arrayList dependiendo del dato (aun no es busqueda binaria)
        String msg = "The product doesn't exist in the list";
        if (option == 1) {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getProductName().equals(data)) {
                    msg = "product: " + products.get(i).getProductName() + " Description : " + products.get(i).getDescription() + " Price: " + products.get(i).getPrice() +
                            " Quantity Available: " + products.get(i).getQuantityAvailable() + " Category : " + products.get(i).getCategory() + " Purchase number :" + products.get(i).getPurchasedNumber();
                    return msg;
                }
            }
        } else if (option == 2) {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getPrice() == Double.parseDouble(data)) {
                    msg = "product: " + products.get(i).getProductName() + " Description : " + products.get(i).getDescription() + " Price: " + products.get(i).getPrice() +
                            " Quantity Available: " + products.get(i).getQuantityAvailable() + " Category : " + products.get(i).getCategory() + " Purchase number :" + products.get(i).getPurchasedNumber();
                    return msg;
                }
            }
            System.out.println("The product doesn't exist in the list");
        } else if (option == 3) {
            Integer.parseInt(data);
            for (int i = 0; i < products.size(); i++) {
                /*if (products.get(i).getCategory() == ) {
                    System.out.println(products.get(i).getProductName());
                    return;
                }*/
                msg = "product: " + products.get(i).getProductName() + " Description : " + products.get(i).getDescription() + " Price: " + products.get(i).getPrice() +
                        " Quantity Available: " + products.get(i).getQuantityAvailable() + " Category : " + products.get(i).getCategory() + " Purchase number :" + products.get(i).getPurchasedNumber();
                return msg;

            }
        } else {
            Integer.parseInt(data);
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getPurchasedNumber() == Integer.parseInt(data)) {
                    msg = "product: " + products.get(i).getProductName() + " Description : " + products.get(i).getDescription() + " Price: " + products.get(i).getPrice() +
                            " Quantity Available: " + products.get(i).getQuantityAvailable() + " Category : " + products.get(i).getCategory() + " Purchase number :" + products.get(i).getPurchasedNumber();
                    return msg;
                }
            }
        }
        return msg;
    }

    public void DeleteProduct(String proName) throws IOException { //Elimina el producto
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductName().equals(proName)) {
                products.set(i, null);
                save();
                System.out.println("Product deleted successfully");
                return;
            }
        }
        System.out.println("The product doesn't exist in the list");
    }

    public void showQuantity(String product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductName().equals(product)) {
                System.out.println("The quantity available of " + products.get(i).getProductName() + " is " + products.get(i).getQuantityAvailable());
                return;
            }
        }
    }

    public void changeQuantity(String product, int quantity) throws IOException {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductName().equals(product)) {
                products.get(i).setQuantityAvailable(quantity);
                System.out.println("The quantity change is successfully");
                System.out.println("The new quantity for " + products.get(i).getProductName() + " is " + products.get(i).getQuantityAvailable());
                save();
                return;
            }
        }
    }
}
