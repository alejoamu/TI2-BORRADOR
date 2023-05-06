package model;

import exceptions.IncompleteDataException;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class ControllerTest {

    Controller controller;
    LocalDate date1 = LocalDate.of(2023, 4, 5);
    LocalDate date2 = LocalDate.of(2023, 4, 12);
    LocalDate date3 = LocalDate.of(2023, 4, 5);
    Order order1 = new Order("Santiago", "PS5, PSP, Audífonos", 2000000.0, date1);
    Order order2 = new Order("James", "Camiseta, Pantaloneta, Guayos", 250000.0, date2);
    Order order3 = new Order("Luis", "XBOX, Mando inalámbrico, Audífonos", 3000000.0, date3);
    Product product1 = new Product("PS5", "VideoGame", 2000000.0, 4, Category.TOYS_AND_GAMES, 2);
    Product product2 = new Product("Hamburguesa", "Pan con carne", 25000.0, 50, Category.FOOD_AND_DRINKS, 3);
    Product product3 = new Product("Camiseta", "Camiseta oversize", 70000.0, 20, Category.CLOTHING_AND_ACCESSORIES, 10);

    public void setupStage1() {
        controller = new Controller();
    }

    public void setupStage2() {
        controller = new Controller();
        controller.getProductList().getProducts().add(product1);
        controller.getProductList().getProducts().add(product2);
        controller.getProductList().getProducts().add(product3);
        controller.getOrderList().getOrders().add(order1);
        controller.getOrderList().getOrders().add(order2);
        controller.getOrderList().getOrders().add(order3);
    }

    @Test
    public void getCategoryTest() {
        // Arrange
        setupStage2();

        // Act
        String expected = String.join("\n",
                "\t[1] BOOKS",
                "\t[2] ELECTRONICS",
                "\t[3] CLOTHING AND ACCESSORIES",
                "\t[4] FOOD AND DRINKS",
                "\t[5] STATIONERY",
                "\t[6] SPORTS",
                "\t[7] BEAUTY AND PERSONAL CARE PRODUCTS",
                "\t[8] TOYS AND GAMES\n");
        String actual = controller.getCategory().replaceAll("\u001B\\[[;\\d]*m", "");

        // Assert
        assertEquals(actual, expected);
    }

    @Test
    public void searchProductByNameTest() {
        // Arrange
        setupStage2();

        // Act - Assert
        assertEquals(controller.searchProduct(1, "PS5"), "Product: PS5 Description: VideoGame Price: 2000000.00 Quantity Available: 4 Category: TOYS_AND_GAMES Purchased Number: 2\n");
    }

    @Test
    public void searchProductByPriceTest() {
        // Arrange
        setupStage2();

        // Act - Assert
        assertEquals(controller.searchProduct(2, "70000.0"), "Product: Camiseta Description: Camiseta oversize Price: 70000.00 Quantity Available: 20 Category: CLOTHING_AND_ACCESSORIES Purchased Number: 10\n");
    }

    @Test
    public void searchProductByCategoryTest() {
        // Arrange
        setupStage2();

        // Act - Assert
        assertEquals(controller.searchProduct(3, "3"), "Product: Hamburguesa Description: Pan con carne Price: 25000.00 Quantity Available: 50 Category: FOOD_AND_DRINKS Purchased Number: 3\n");
    }

    @Test
    public void searchProductByPurchasedNumberTest() {
        // Arrange
        setupStage2();

        // Act - Assert
        assertEquals(controller.searchProduct(4, "3"), "Product: Hamburguesa Description: Pan con carne Price: 25000.00 Quantity Available: 50 Category: FOOD_AND_DRINKS Purchased Number: 3\n");
    }

    @Test
    public void searchProductByEmptyNameTest() {
        // Arrange
        setupStage2();

        // Act - Assert
        assertThrows(IncompleteDataException.class, () -> {
            controller.searchProduct(1, "");
        });
    }

    @Test
    public void searchProductByEmptyPriceTest() {
        // Arrange
        setupStage2();

        // Act - Assert
        assertThrows(IncompleteDataException.class, () -> {
            controller.searchProduct(2, " ");
        });
    }

    @Test
    public void searchProductByEmptyCategoryTest() {
        // Arrange
        setupStage2();

        // Act - Assert
        assertThrows(NumberFormatException.class, () -> {
            controller.searchProduct(3, "");
        });
    }

    @Test
    public void searchProductByEmptyPurchasedNumberTest() {
        // Arrange
        setupStage2();

        // Act - Assert
        assertThrows(IncompleteDataException.class, () -> {
            controller.searchProduct(4, "");
        });
    }

    @Test
    public void searchProductByNameNotFoundTest() {
        // Arrange
        setupStage2();

        // Act
        String expected = "NO PRODUCT HAS THAT CHARACTERISTIC";
        String actual = controller.searchProduct(1, "XBOX").replaceAll("\u001B\\[[;\\d]*m", "").trim();

        // Assert
        assertEquals(actual, expected);
    }

    @Test
    public void searchProductByPriceNotFoundTest() {
        // Arrange
        setupStage2();

        // Act
        String expected = "NO PRODUCT HAS THAT CHARACTERISTIC";
        String actual = controller.searchProduct(2, "10000.0").replaceAll("\u001B\\[[;\\d]*m", "").trim();

        // Assert
        assertEquals(actual, expected);
    }

    @Test
    public void searchProductByCategoryNotFoundTest() {
        // Arrange
        setupStage2();

        // Act
        String expected = "NO PRODUCT HAS THAT CHARACTERISTIC";
        String actual = controller.searchProduct(3, "0").replaceAll("\u001B\\[[;\\d]*m", "").trim();

        // Assert
        assertEquals(actual, expected);
    }

    @Test
    public void searchProductByPurchasedNumberNotFoundTest() {
        // Arrange
        setupStage2();

        // Act
        String expected = "NO PRODUCT HAS THAT CHARACTERISTIC";
        String actual = controller.searchProduct(4, "5").replaceAll("\u001B\\[[;\\d]*m", "").trim();

        // Assert
        assertEquals(actual, expected);
    }

    @Test
    public void searchOrderByBuyerNameTest() {
        // Arrange
        setupStage2();

        // Act - Assert
        assertEquals(controller.searchOrder(1, "Santiago"), "Buyer: Santiago Products list: PS5, PSP, Audífonos Total price: 2000000.0 Purchase date: 2023-04-05");
    }

    @Test
    public void searchOrderByTotalPriceTest() {
        // Arrange
        setupStage2();

        // Act - Assert
        assertEquals(controller.searchOrder(2, "3000000.0"), "Buyer: Luis Products list: XBOX, Mando inalámbrico, Audífonos Total price: 3000000.0 Purchase date: 2023-04-05");
    }

    @Test
    public void searchOrderByEmptyBuyerNameTest() {
        // Arrange
        setupStage2();

        // Act - Assert
        assertThrows(IncompleteDataException.class, () -> {
            controller.searchOrder(1, "");
        });
    }

    @Test
    public void searchOrderByEmptyTotalPriceTest() {
        // Arrange
        setupStage2();

        // Act - Assert
        assertThrows(IncompleteDataException.class, () -> {
            controller.searchOrder(2, "");
        });
    }

    @Test
    public void searchOrderByBuyerNameNotFoundTest() {
        // Arrange
        setupStage2();

        // Act - Assert
        assertEquals(controller.searchOrder(1, "Juan"), "the order doesn't exist in the list");
    }

    @Test
    public void searchOrderByTotalPriceNotFoundTest() {
        // Arrange
        setupStage2();

        // Act - Assert
        assertEquals(controller.searchOrder(2, "1000000.0"), "the order doesn't exist in the list");
    }

}
