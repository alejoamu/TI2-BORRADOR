package model;

import color.Color;
import com.google.gson.Gson;
import exceptions.DateFormatException;
import exceptions.IncompleteDataException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;

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

    public void show() {
        for (Product p : products) { //P es cada elemento de la lista
            System.out.println(p.getProductName());
            return;
        }
    }

    public boolean changeQuantity(String product, int quantity) {
        // Sort by name ascending
        Comparator<Product> byName = (p1, p2) -> p1.getProductName().compareToIgnoreCase(p2.getProductName());
        products.sort(byName);
        // Search for the product by its name
        int index = binarySearch.search(products, byName, new Product(product, "---", Integer.MAX_VALUE, Integer.MAX_VALUE, Category.BOOKS, Integer.MAX_VALUE), 0, products.size() - 1);
        if (index == -1) {
            return false;
        } else {
            System.out.println(products.get(index).getQuantityAvailable());
            products.get(index).setQuantityAvailable(quantity);
            System.out.println(products.get(index).getQuantityAvailable());
            return true;
        }

    }

    public String searchProduct(int option, String data) {
        // String msg = Color.BOLD + Color.YELLOW + "                   PRODUCT IS NOT REGISTERED                   " + Color.RESET;
        StringBuilder msg = new StringBuilder();
        switch (option) {
            case 1:
                Product product = searchProductByName(data);
                if (product != null)
                    msg = new StringBuilder(String.format("Product: %s Description: %s Price: %.2f Quantity Available: %d Category: %s Purchase Number: %d", product.getProductName(), product.getDescription(), product.getPrice(), product.getQuantityAvailable(), product.getCategory(), product.getPurchasedNumber())).append("\n");
                return msg.toString();
            case 2:
                double price = -1;
                try {
                    price = Double.parseDouble(data);
                } catch (NumberFormatException ex) {
                    throw new IncompleteDataException();
                }
                ArrayList<Product> productsFound = searchProductByPrice(price);
                for (Product p : productsFound) {
                    msg.append(String.format("Product: %s Description: %s Price: %.2f Quantity Available: %d Category: %s Purchase Number: %d", p.getProductName(), p.getDescription(), p.getPrice(), p.getQuantityAvailable(), p.getCategory(), p.getPurchasedNumber())).append("\n");
                }
                break;
            case 3:

                break;
            case 4:

                break;
        }
        /*if (option == 3) {
            Integer.parseInt(data);
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getCategory() == ) {
                    System.out.println(products.get(i).getProductName());
                    return;
                }
                msg = new StringBuilder("product: " + products.get(i).getProductName() + " Description : " + products.get(i).getDescription() + " Price: " + products.get(i).getPrice() +
                        " Quantity Available: " + products.get(i).getQuantityAvailable() + " Category : " + products.get(i).getCategory() + " Purchase number :" + products.get(i).getPurchasedNumber());
                return msg.toString();

            }
        } else {
            Integer.parseInt(data);
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getPurchasedNumber() == Integer.parseInt(data)) {
                    msg = new StringBuilder("product: " + products.get(i).getProductName() + " Description : " + products.get(i).getDescription() + " Price: " + products.get(i).getPrice() +
                            " Quantity Available: " + products.get(i).getQuantityAvailable() + " Category : " + products.get(i).getCategory() + " Purchase number :" + products.get(i).getPurchasedNumber());
                    return msg.toString();
                }
            }
        }*/
        return msg.toString();
    }

    public Product searchProductByName(String nameProduct) {
        // Sort by name ascending
        Comparator<Product> byName = (p1, p2) -> p1.getProductName().compareToIgnoreCase(p2.getProductName());
        products.sort(byName);
        // Search for the product by its name
        int index = binarySearch.search(products, byName, new Product(nameProduct, "---", Integer.MAX_VALUE, Integer.MAX_VALUE, Category.BOOKS, Integer.MAX_VALUE), 0, products.size() - 1);
        if (index == -1) return null;
        else return products.get(index);
    }

    public ArrayList<Product> searchProductByPrice(double price) {
        // Sort by price ascending
        Comparator<Product> byPrice = (p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice());
        products.sort(byPrice);
        // Search for products with the specified price using binary search
        ArrayList<Product> result = new ArrayList<>();
        int index = binarySearch.search(products, byPrice, new Product("---", "---", price, Integer.MAX_VALUE, Category.BOOKS, Integer.MAX_VALUE), 0, products.size() - 1);
        if (index != -1) {
            // Add the product at the found index to the result list
            result.add(products.get(index));
            // Search for any other products with the same price that appear before the found index
            int leftIndex = index - 1;
            while (leftIndex >= 0 && byPrice.compare(products.get(leftIndex), result.get(0)) == 0) {
                result.add(0, products.get(leftIndex));
                leftIndex--;
            }
            // Search for any other products with the same price that appear after the found index
            int rightIndex = index + 1;
            while (rightIndex < products.size() && byPrice.compare(products.get(rightIndex), result.get(result.size() - 1)) == 0) {
                result.add(products.get(rightIndex));
                rightIndex++;
            }
        }
        return result;
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

}
