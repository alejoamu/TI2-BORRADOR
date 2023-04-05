package Model;

import java.util.Date;

public class Order {
    private String buyerName;
    private String productList;
    private double totalPrice;
    private Date purchasedDate;

    public Order(String buyerName, String productList, double totalPrice/*,Date purchasedDate*/) {
        this.buyerName = buyerName;
        this.productList = productList;
        this.totalPrice = totalPrice;
        //this.purchasedDate = purchasedDate;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getProductList() {
        return productList;
    }

    public void setProductList(String productList) {
        this.productList = productList;
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
