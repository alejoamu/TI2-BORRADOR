package model;

import exceptions.DateFormatException;
import exceptions.IncompleteDataException;
import exceptions.NegativeNumberException;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class OrderListTest {

    OrderList orderList;
    LocalDate date1 = LocalDate.of(2023, 4, 5);
    LocalDate date2 = LocalDate.of(2023, 4, 12);
    LocalDate date3 = LocalDate.of(2023, 4, 5);
    String[] products1 = {"PS5", "PSP", "Audífonos"};
    String[] products2 = {"Camiseta", "Pantaloneta", "Guayos"};
    String[] products3 = {"XBOX", "Mando inalámbrico", "Audífonos"};
    String[] productsQuantity1 = {"2", "3", "2"};
    String[] productsQuantity2 = {"3", "1", "2"};
    String[] productsQuantity3 = {"4", "2", "1"};
    Order order1 = new Order("Santiago", products1, productsQuantity1,2000000.0, date1);
    Order order2 = new Order("James", products2, productsQuantity2,250000.0, date2);
    Order order3 = new Order("Luis", products3, productsQuantity3,3000000.0, date3);

    public void setupStage1() {
        orderList = new OrderList();
    }

    public void setupStage2() {
        orderList = new OrderList();
        orderList.getOrders().add(order1);
        orderList.getOrders().add(order2);
    }

    public void setupStage3() {
        orderList = new OrderList();
        orderList.getOrders().add(order1);
        orderList.getOrders().add(order2);
        orderList.getOrders().add(order3);
    }

    @Test
    public void addOrderTest() {
        // Arrange
        setupStage1();

        // Act
        orderList.getOrders().add(order2);

        // Assert
        assertEquals(orderList.getOrders().size(), 1);
    }

    @Test
    public void addOrderNegativeTotalPriceTest() {
        // Arrange
        setupStage1();

        // Act - Assert
        assertThrows(NegativeNumberException.class, () -> {
            orderList.getOrders().add(new Order("James", products1, productsQuantity1,-250000.0, date2));
        });
        assertEquals(orderList.getOrders().size(), 0);
    }

    @Test
    public void searchOrderByBuyerName() {
        // Arrange
        setupStage3();

        // Act - Assert
        assertEquals(orderList.searchOrder(1, "James", 0, 0), "BuyerName: James ProductsOrder: [Camiseta, Pantaloneta, Guayos] TotalPrice: 250000.00 Products Quantity: [3, 1, 2] Purchase Date: 2023-04-12 \n");
    }

    @Test
    public void searchOrderByNameNotFoundTest() {
        // Arrange
        setupStage3();

        // Act - Assert
        assertThrows(IncompleteDataException.class, () -> {
            orderList.searchOrder(1, "", 0, 0);
        });
    }

    @Test
    public void searchOrderByTotalPrice() {
        // Arrange
        setupStage3();

        // Act - Assert
        assertEquals(orderList.searchOrder(2, "2000000.0", 0, 0), "BuyerName: Santiago ProductsOrder: [PS5, PSP, Audífonos] TotalPrice: 2000000.00 Products Quantity: [2, 3, 2] Purchase Date: 2023-04-05 \n");
    }

    @Test
    public void searchOrderByNegativeTotalPrice() {
        // Arrange
        setupStage3();

        // Act - Assert
        assertThrows(NegativeNumberException.class, () -> {
            orderList.searchOrder(2, "-2000000.0", 0, 0);
        });
    }

    @Test
    public void searchOrderByDate() {
        // Arrange
        setupStage3();

        // Act - Assert
        assertEquals(orderList.searchOrder(3, "2023-04-05", 0, 0), "BuyerName: Santiago ProductsOrder: [PS5, PSP, Audífonos] TotalPrice: 2000000.00 Products Quantity: [2, 3, 2] Purchase Date: 2023-04-05 \n" +
                "BuyerName: Luis ProductsOrder: [XBOX, Mando inalámbrico, Audífonos] TotalPrice: 3000000.00 Products Quantity: [4, 2, 1] Purchase Date: 2023-04-05 \n");
    }

    @Test
    public void searchOrderByWrongDate() {
        // Arrange
        setupStage3();

        // Act - Assert
        assertThrows(DateFormatException.class, () -> {
            orderList.searchOrder(3, "2023", 0, 0);
        });
    }

}
