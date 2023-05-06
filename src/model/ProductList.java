package model;

import color.Color;
import com.google.gson.Gson;
import exceptions.IncompleteDataException;

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

    public void show() {
        for (Product p : products) { //P es cada elemento de la lista
            System.out.println(p.getProductName());
            return;
        }
    }

    public boolean changeQuantity(String product, int quantity) {
        Product result = searchProductByName(product);
        if (result == null) {
            return false;
        } else {
            result.addQuantityAvailable(quantity);
            return true;
        }
    }

    public String searchProduct(int option, String data) {
        StringBuilder msg = new StringBuilder();
        switch (option) {
            case 1:
                Product product = searchProductByName(data);
                if (product != null)
                    msg.append(String.format("Product: %s Description: %s Price: %.2f Quantity Available: %d Category: %s Purchased Number: %d", product.getProductName(), product.getDescription(), product.getPrice(), product.getQuantityAvailable(), product.getCategory(), product.getPurchasedNumber())).append("\n");
                break;
            case 2:
                double price;
                try {
                    price = Double.parseDouble(data);
                } catch (NumberFormatException ex) {
                    throw new IncompleteDataException();
                }
                ArrayList<Product> productsFoundPrice = searchProductByPrice(price);
                for (Product p : productsFoundPrice) {
                    msg.append(String.format("Product: %s Description: %s Price: %.2f Quantity Available: %d Category: %s Purchased Number: %d", p.getProductName(), p.getDescription(), p.getPrice(), p.getQuantityAvailable(), p.getCategory(), p.getPurchasedNumber())).append("\n");
                }
                break;
            case 3:
                ArrayList<Product> productsFoundCategory = searchProductByCategory(Category.values()[Integer.parseInt(data)]);
                for (Product p : productsFoundCategory) {
                    msg.append(String.format("Product: %s Description: %s Price: %.2f Quantity Available: %d Category: %s Purchased Number: %d", p.getProductName(), p.getDescription(), p.getPrice(), p.getQuantityAvailable(), p.getCategory(), p.getPurchasedNumber())).append("\n");
                }
                break;
            case 4:
                int purchasedNumber;
                try {
                    purchasedNumber = Integer.parseInt(data);
                } catch (NumberFormatException ex) {
                    throw new IncompleteDataException();
                }
                ArrayList<Product> productsFoundPurchasedNumber = searchProductByPurchasedNumber(purchasedNumber);
                for (Product p : productsFoundPurchasedNumber) {
                    msg.append(String.format("Product: %s Description: %s Price: %.2f Quantity Available: %d Category: %s Purchased Number: %d", p.getProductName(), p.getDescription(), p.getPrice(), p.getQuantityAvailable(), p.getCategory(), p.getPurchasedNumber())).append("\n");
                }
                break;
        }
        if (msg.length() == 0) {
            return Color.BOLD + Color.YELLOW + "              NO PRODUCT HAS THAT CHARACTERISTIC               \n" + Color.RESET;
        }
        return msg.toString();
    }

    public Product searchProductByName(String nameProduct) {
        // Sort by name ascending
        Comparator<Product> byName = (p1, p2) -> p1.getProductName().compareToIgnoreCase(p2.getProductName());
        products.sort(byName);
        // Search for the product by its name
        int index = binarySearch.search(products, byName, new Product(nameProduct, "---", Double.MAX_VALUE, Integer.MAX_VALUE, Category.BOOKS, Integer.MAX_VALUE), 0, products.size() - 1);
        if (index == -1) return null;
        else return products.get(index);
    }

    public ArrayList<Product> searchProductByPrice(double price) {
        // Sort by price ascending
        Comparator<Product> byPrice = (p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice());
        products.sort(byPrice);
        // Search for products with the specified price
        return binarySearch.searchObjectsByProperty(products, byPrice, new Product("---", "---", price, Integer.MAX_VALUE, Category.BOOKS, Integer.MAX_VALUE));
    }

    public ArrayList<Product> searchProductByCategory(Category category) {
        // Sort by category ascending
        Comparator<Product> byCategory = (p1, p2) -> p1.getCategory().compareTo(p2.getCategory());
        products.sort(byCategory);
        // Search for products with the specified category
        return binarySearch.searchObjectsByProperty(products, byCategory, new Product("---", "---", Double.MAX_VALUE, Integer.MAX_VALUE, category, Integer.MAX_VALUE));
    }

    public ArrayList<Product> searchProductByPurchasedNumber(int purchasedNumber) {
        // Sort by purchased number ascending
        Comparator<Product> byPurchasedNumber = (p1, p2) -> Integer.compare(p1.getPurchasedNumber(), p2.getPurchasedNumber());
        products.sort(byPurchasedNumber);
        // Search for products with the specified purchased number
        return binarySearch.searchObjectsByProperty(products, byPurchasedNumber, new Product("---", "---", Double.MAX_VALUE, Integer.MAX_VALUE, Category.BOOKS, purchasedNumber));
    }

    //----------------------------------------------------------------

    public String searchProduct(int option, String minData, String maxData) {
        StringBuilder msg = new StringBuilder();
        switch (option) {
            case 1:
                /*Product product = searchProductByName(data);
                if (product != null)
                    msg = new StringBuilder(String.format("Product: %s Description: %s Price: %.2f Quantity Available: %d Category: %s Purchased Number: %d", product.getProductName(), product.getDescription(), product.getPrice(), product.getQuantityAvailable(), product.getCategory(), product.getPurchasedNumber())).append("\n");
                */
                break;
            case 2:
                double priceMin = -1, priceMax = -1;
                try {
                    priceMin = Double.parseDouble(minData);
                    priceMax = Double.parseDouble(maxData);
                } catch (NumberFormatException ex) {
                    throw new IncompleteDataException();
                }
                ArrayList<Product> productsFoundPrice;
                if (priceMin < priceMax) {
                    productsFoundPrice = searchProductByPrice(priceMin, priceMax);
                } else if (priceMin > priceMax) {
                    productsFoundPrice = searchProductByPrice(priceMax, priceMin);
                } else {
                    productsFoundPrice = searchProductByPrice(priceMax);
                }
                for (Product p : productsFoundPrice) {
                    msg.append(String.format("Product: %s Description: %s Price: %.2f Quantity Available: %d Category: %s Purchased Number: %d", p.getProductName(), p.getDescription(), p.getPrice(), p.getQuantityAvailable(), p.getCategory(), p.getPurchasedNumber())).append("\n");
                }
                break;
            case 3:
                /*ArrayList<Product> productsFoundCategory = searchProductByCategory(Category.values()[Integer.parseInt(data)]);
                for (Product p : productsFoundCategory) {
                    msg.append(String.format("Product: %s Description: %s Price: %.2f Quantity Available: %d Category: %s Purchased Number: %d", p.getProductName(), p.getDescription(), p.getPrice(), p.getQuantityAvailable(), p.getCategory(), p.getPurchasedNumber())).append("\n");
                }*/
                break;
            case 4:/*
                int purchasedNumber = -1;
                try {
                    purchasedNumber = Integer.parseInt(data);
                } catch (NumberFormatException ex) {
                    throw new IncompleteDataException();
                }
                ArrayList<Product> productsFoundPurchasedNumber = searchProductByPurchasedNumber(purchasedNumber);
                for (Product p : productsFoundPurchasedNumber) {
                    msg.append(String.format("Product: %s Description: %s Price: %.2f Quantity Available: %d Category: %s Purchased Number: %d", p.getProductName(), p.getDescription(), p.getPrice(), p.getQuantityAvailable(), p.getCategory(), p.getPurchasedNumber())).append("\n");
                }*/
                break;
        }
        if (msg.length() == 0) {
            return Color.BOLD + Color.YELLOW + "              NO PRODUCT HAS THAT CHARACTERISTIC               \n" + Color.RESET;
        }
        return msg.toString();
    }

    public ArrayList<Product> searchProductByPrice(double minPrice, double maxPrice) {
        // Sort by price ascending
        Comparator<Product> byPrice = (p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice());
        products.sort(byPrice);
        // Search for products within the specified price range using binary search
        ArrayList<Product> result = new ArrayList<>();
        int startIndex = binarySearch.search(products, byPrice, new Product("---", "---", minPrice, Integer.MAX_VALUE, Category.BOOKS, Integer.MAX_VALUE), 0, products.size() - 1);
        if (startIndex != -1) {
            // Search for any products whose price is equal to or greater than the minimum price
            int currentIndex = startIndex;
            while (currentIndex < products.size() && products.get(currentIndex).getPrice() <= maxPrice) {
                if (products.get(currentIndex).getPrice() >= minPrice) {
                    result.add(products.get(currentIndex));
                }
                currentIndex++;
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

}
