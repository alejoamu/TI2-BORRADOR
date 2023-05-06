package model;

import color.Color;
import exceptions.NegativeNumberException;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ProductListTest {

    ProductList productList;

    Product product1 = new Product("PS5", "VideoGame", 2000000.0, 4, Category.TOYS_AND_GAMES, 2);
    Product product2 = new Product("Hamburguesa", "Pan con carne", 25000.0, 50, Category.FOOD_AND_DRINKS, 3);
    Product product3 = new Product("Camiseta", "Camiseta oversize", 70000.0, 20, Category.CLOTHING_AND_ACCESSORIES, 10);
    Product product4 = new Product("XBOX", "VideoGame", 2500000.0, 7, Category.TOYS_AND_GAMES, 3);
    Product product5 = new Product("Balón", "Balón de fútbol", 50000.0, 20, Category.SPORTS, 5);
    public void setupStage1() {productList = new ProductList();}
    public void setupStage2() {
        productList = new ProductList();
        productList.getProducts().add(product1);
        productList.getProducts().add(product2);
        productList.getProducts().add(product3);
    }

    public void setupStage3() {
        productList = new ProductList();
        productList.getProducts().add(product1);
        productList.getProducts().add(product2);
        productList.getProducts().add(product3);
        productList.getProducts().add(product4);
        productList.getProducts().add(product5);
    }

    @Test
    public void addProductTest() {
        // Arrange
        setupStage1();
        // Act
        productList.getProducts().add(product1);
        // Assert
        assertEquals(productList.getProducts().size(), 1);
    }

    @Test
    public void addProductNegativePriceTest() {
        // Arrange
        setupStage1();
        // Act - Assert
        assertThrows(NegativeNumberException.class, () -> {
            productList.getProducts().add(new Product("PS5", "VideoGame", -2000000.0, 4, Category.TOYS_AND_GAMES, 2));
        });
    }

    @Test
    public void addProductNegativeQuantityAvailableTest() {
        // Arrange
        setupStage1();
        // Act - Assert
        assertThrows(NegativeNumberException.class, () -> {
            productList.getProducts().add(new Product("PS5", "VideoGame", 2000000.0, -4, Category.TOYS_AND_GAMES, 2));
        });
    }

    @Test
    public void addProductNegativePurchasedNumberTest() {
        // Arrange
        setupStage1();
        // Act - Assert
        assertThrows(NegativeNumberException.class, () -> {
            productList.getProducts().add(new Product("PS5", "VideoGame", 2000000.0, 4, Category.TOYS_AND_GAMES, -2));
        });
    }

    @Test
    public void searchProductByName() {
        // Arrange
        setupStage3();

        // Act - Assert
        assertEquals(productList.searchProduct(1, "Hamburguesa"), "Product: Hamburguesa Description: Pan con carne Price: 25000.00 Quantity Available: 50 Category: FOOD_AND_DRINKS Purchased Number: 3\n");
    }

    @Test
    public void searchProductByNameNotFoundTest() {
        // Arrange
        setupStage3();

        // Act - Assert
        assertEquals(productList.searchProduct(1, " "), Color.BOLD + Color.YELLOW + "              NO PRODUCT HAS THAT CHARACTERISTIC               \n" + Color.RESET);
    }

    @Test
    public void searchProductByPriceTest() {
        // Arrange
        setupStage3();
        ArrayList<Product> result = new ArrayList<>();
        result.add(product3);

        // Act - Assert
        assertEquals(productList.searchProductByPrice(70000.0), result);
    }

    @Test
    public void searchProductByNegativePriceTest() {
        // Arrange
        setupStage3();

        // Act - Assert
        assertThrows(NegativeNumberException.class, () -> {
            productList.searchProductByPrice(-70000.0);
        });
    }

    @Test
    public void searchProductByCategoryTest() {
        // Arrange
        setupStage3();
        ArrayList<Product> result = new ArrayList<>();
        result.add(product1);
        result.add(product4);

        // Act - Assert
        assertEquals(productList.searchProductByCategory(Category.TOYS_AND_GAMES), result);
    }

    @Test
    public void searchProductListByPurchasedNumberTest() {
        // Arrange
        setupStage3();
        ArrayList<Product> result = new ArrayList<>();
        result.add(product5);

        // Act - Assert
        assertEquals(productList.searchProductByPurchasedNumber(5), result);
    }

    @Test
    public void searchProductListByNegativePurchasedNumberTest() {
        // Arrange
        setupStage3();

        // Act - Assert
        assertThrows(NegativeNumberException.class, () -> {
            productList.searchProductByPurchasedNumber(-5);
        });
    }
}
