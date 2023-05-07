package model;

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

    public void setupStage1() {
        productList = new ProductList();
    }

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
    public void searchProductByExactNameTest() {
        // Arrange
        setupStage3();

        // Act - Assert
        assertEquals(productList.searchProduct(1, "Hamburguesa",  0, 0), "Product: Hamburguesa Description: Pan con carne Price: 25000.00 Quantity Available: 50 Category: FOOD_AND_DRINKS Purchased Number: 3\n");
    }

    @Test
    public void searchProductByExactNameNotFoundTest() {
        // Arrange
        setupStage3();

        // Act
        String result = productList.searchProduct(1, " ",  0, 0);

        // Assert
        assertTrue(result.contains("NO PRODUCT HAS THAT CHARACTERISTIC"));
    }

    @Test
    public void searchProductByExactPriceTest() {
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
    public void searchProductByExactCategoryTest() {
        // Arrange
        setupStage3();
        ArrayList<Product> result = new ArrayList<>();
        result.add(product1);
        result.add(product4);

        // Act - Assert
        assertEquals(productList.searchProductByCategory(Category.TOYS_AND_GAMES), result);
    }

    @Test
    public void searchProductListByExactPurchasedNumberTest() {
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

    @Test
    public void searchProductByIntervalNameTest() {
        // Arrange
        setupStage3();
        ArrayList<Product> result = new ArrayList<>();
        result.add(product5);
        result.add(product3);

        // Act - Assert
        assertEquals(productList.searchProductByName("A", "C"), result);
    }

    @Test
    public void searchProductByWrongIntervalNameTest() {
        // Arrange
        setupStage3();

        // Act - Assert
        assertEquals(productList.searchProductByName("1", "?"), null);
    }

    @Test
    public void searchMultipleProductsByIntervalNameTest() {
        // Arrange
        setupStage3();
        ArrayList<Product> result = new ArrayList<>();
        result.add(product5);
        result.add(product3);
        result.add(product2);
        result.add(product1);
        result.add(product4);

        // Act - Assert
        assertEquals(productList.searchProductByName("A", "Z"), result);
    }

    @Test
    public void searchProductByRangePriceTest() {
        // Arrange
        setupStage3();
        ArrayList<Product> result = new ArrayList<>();
        result.add(product1);
        result.add(product4);

        // Act - Assert
        assertEquals(productList.searchProductByPrice(1000000, 2500000), result);
    }

    @Test
    public void searchProductByNegativeRangePriceTest() {
        // Arrange
        setupStage3();

        // Act - Assert
        assertThrows(NegativeNumberException.class, () -> {
            productList.searchProductByPrice(-100000, -250000);
        });
    }

    @Test
    public void searchProductByRangeQuantityAvailableTest() {
        // Arrange
        setupStage3();
        ArrayList<Product> result = new ArrayList<>();
        result.add(product4);
        result.add(product3);
        result.add(product5);

        // Act - Assert
        assertEquals(productList.searchProductByQuantityAvailable(5, 20), result);
    }

    @Test
    public void searchProductByNegativeRangeQuantityAvailableTest() {
        // Arrange
        setupStage3();

        // Act - Assert
        assertThrows(NegativeNumberException.class, () -> {
            productList.searchProductByQuantityAvailable(-10, 0);
        });
    }

    @Test
    public void searchProductByRangePurchasedNumberTest() {
        // Arrange
        setupStage3();
        ArrayList<Product> result = new ArrayList<>();
        result.add(product1);
        result.add(product2);
        result.add(product4);
        result.add(product5);
        result.add(product3);

        // Act - Assert
        assertEquals(productList.searchProductByPurchasedNumber(1, 10), result);
    }

    @Test
    public void searchProductByNegativeRangePurchasedNumberTest() {
        // Arrange
        setupStage3();

        // Act - Assert
        assertThrows(NegativeNumberException.class, () -> {
            productList.searchProductByPurchasedNumber(-1, -50);
        });
    }

    @Test
    public void searchProductDescendingOrder() {
        // Arrange
        setupStage3();
        ArrayList<Product> result = new ArrayList<>();
        result.add(product5);
        result.add(product3);

        // Act - Assert
        assertEquals(productList.searchProduct(1, "A", "C", 2, 1), "Product: Camiseta Description: Camiseta oversize Price: 70000.00 Quantity Available: 20 Category: CLOTHING_AND_ACCESSORIES Purchased Number: 10\n" +
                "Product: Balón Description: Balón de fútbol Price: 50000.00 Quantity Available: 20 Category: SPORTS Purchased Number: 5\n");
    }

    @Test
    public void searchProductAscendigOrder() {
        // Arrange
        setupStage3();

        // Act - Assert
        assertEquals(productList.searchProduct(2, "1000000", "2500000", 1, 2), "Product: PS5 Description: VideoGame Price: 2000000.00 Quantity Available: 4 Category: TOYS_AND_GAMES Purchased Number: 2\n" +
                "Product: XBOX Description: VideoGame Price: 2500000.00 Quantity Available: 7 Category: TOYS_AND_GAMES Purchased Number: 3\n");
    }
}
