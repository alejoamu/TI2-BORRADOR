package Model;

import java.util.Date;

public class Order {
    private String buyerName;
    private String productsOrder;
    private double totalPrice;
    private Date purchasedDate;

    public Order(String buyerName, String productsOrder, double totalPrice/*,Date purchasedDate*/) {
        this.buyerName = buyerName;
        this.productsOrder = productsOrder;
        this.totalPrice = totalPrice;
        //this.purchasedDate = purchasedDate;
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

    public Date getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(Date purchasedDate) {
        this.purchasedDate = purchasedDate;
    }
}
