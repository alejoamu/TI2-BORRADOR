package model;

import exceptions.NegativeNumberException;

import java.time.LocalDate;
import java.util.Date;

public class Order {
    private String buyerName;
    private String productsOrder;
    private double totalPrice;
    private LocalDate purchasedDate;

    public Order(String buyerName, String productsOrder, double totalPrice, LocalDate date) {
        if(totalPrice < 0){
            throw new NegativeNumberException();
        }
        this.totalPrice = totalPrice;
        this.buyerName = buyerName;
        this.productsOrder = productsOrder;
        this.purchasedDate = date;
    }

    @Override
    public String toString() {
        return buyerName + " " + productsOrder + " " + totalPrice + " " + purchasedDate;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getProductsOrder() {
        return productsOrder;
    }

    public void setProductsOrder(String productsOrder) {
        this.productsOrder = productsOrder;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(LocalDate purchasedDate) {
        this.purchasedDate = purchasedDate;
    }
}