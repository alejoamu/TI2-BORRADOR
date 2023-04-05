package Model;

public class Product {
    private String productName;
    private String Description;
    private Double price;
    private int QuantityAvailable;
    private Category category;
    private int purchasedNumber;

    public Product(String productName, String description, Double price, int quantityAvailable, Category category, int purchasedNumber) {
        this.productName = productName;
        this.Description = description;
        this.price = price;
        this.QuantityAvailable = quantityAvailable;
        this.category = category;
        this.purchasedNumber = purchasedNumber;
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
        return QuantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        QuantityAvailable = quantityAvailable;
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
