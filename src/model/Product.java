package model;

import exceptions.NegativeNumberException;

public class Product {
    private String productName;
    private String Description;
    private Double price;
    private int quantityAvailable;
    private Category category;
    private int purchasedNumber;

    public Product(String productName, String description, Double price, int quantityAvailable, Category category, int purchasedNumber) {
        if((price < 0) || (quantityAvailable < 0) || (purchasedNumber < 0)){
            throw new NegativeNumberException();
        }
        this.productName = productName;
        this.Description = description;
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
        quantityAvailable -= quantity2Subtract;
    }

    @Override
    public String toString() {
        return productName + " " + Description + " " + price + " " + quantityAvailable + " "+ category + " " + purchasedNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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
