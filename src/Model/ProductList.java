package Model;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ProductList {

    static String folder = "dataBase";
    static String path = "dataBase/products.txt";
    ArrayList<Product> products;

    public ProductList() {
        products = new ArrayList<>();
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
        writer.write(data); // hace que se guarde en el buffer
        writer.flush(); // hace que el buffer se limpie
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
            //System.out.println(content);
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

    public void searchProduct(int option, String data) { //Busca el producto dentro del arrayList dependiendo del dato (aun no es busqueda binaria)
        if (option == 1) {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getProductName().equals(data)) {
                    System.out.println(products.get(i).getProductName());
                    return;
                }
            }
            System.out.println("The product doesn't exist in the list");
        } else if (option == 2) {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getPrice() == Double.parseDouble(data)) {
                    System.out.println(products.get(i).getProductName());
                    return;
                }
            }
            System.out.println("The product doesn't exist in the list");
        } else if (option == 3) {
            Integer.parseInt(data);
            for (int i = 0; i < products.size(); i++) {
                return;
            }
            System.out.println("The product doesn't exist in the list");
        } else {
            Integer.parseInt(data);
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getPurchasedNumber() == Integer.parseInt(data)) {
                    System.out.println(products.get(i).getProductName());
                    return;
                }
            }
            System.out.println("The product doesn't exist in the list");
        }
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
                System.out.println("The quantity available of " + products.get(i).getProductName() + " is "+products.get(i).getQuantityAvailable());
                return;
            }
        }
    }
    public void changeQuantity(String product,int quantity)throws IOException{
        for (int i=0; i< products.size(); i++){
            if (products.get(i).getProductName().equals(product)){
                products.get(i).setQuantityAvailable(quantity);
                System.out.println("The quantity change is successfully");
                System.out.println("The new quantity for "+products.get(i).getProductName()+" is "+products.get(i).getQuantityAvailable());
                save();
                return;
            }
        }
    }
}
