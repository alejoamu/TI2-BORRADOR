package model;

import exceptions.IncompleteDataException;
import exceptions.NegativeNumberException;
import exceptions.QuantityExceededException;

public class Product {

    private String productName;
    private String description;
    private double price;
    private int quantityAvailable;
    private Category category;
    private int purchasedNumber;

    public Product(String productName, String description, double price, int quantityAvailable, Category category, int purchasedNumber) {
        if ((productName.equals("")) || (description.equals(""))) {
            throw new IncompleteDataException();
        }
        if((price < 0) || (quantityAvailable < 0) || (purchasedNumber < 0)){
            throw new NegativeNumberException();
        }
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
        this.category = category;
        this.purchasedNumber = purchasedNumber;
    }

    public void addQuantityAvailable(int quantity2Add){
        if(quantity2Add < 0){
            throw new NegativeNumberException();
        }
        quantityAvailable += quantity2Add;
    }

    public void subtractQuantityAvailable(int quantity2Subtract){
        if(quantity2Subtract < 0){
            throw new NegativeNumberException();
        }
        if(quantity2Subtract > quantityAvailable) {
            throw new QuantityExceededException();
        }
        quantityAvailable -= quantity2Subtract;
    }

    @Override
    public String toString() {
        return productName + " " + description + " " + price + " " + quantityAvailable + " "+ category + " " + purchasedNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getPurchasedNumber() {
        return purchasedNumber;
    }

    public void setPurchasedNumber(int purchasedNumber) {
        this.purchasedNumber = purchasedNumber;
    }

}