package model;

import exceptions.DateFormatException;
import exceptions.IncompleteDataException;
import exceptions.NegativeNumberException;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

/*public class OrderListTest {

    OrderList orderList;
    LocalDate date1 = LocalDate.of(2023, 4, 5);
    LocalDate date2 = LocalDate.of(2023, 4, 12);
    LocalDate date3 = LocalDate.of(2023, 4, 5);
    //Order order1 = new Order("Santiago", "PS5, PSP, Audífonos", 2000000.0, date1);
    //Order order2 = new Order("James", "Camiseta, Pantaloneta, Guayos", 250000.0, date2);
    //Order order3 = new Order("Luis", "XBOX, Mando inalámbrico, Audífonos", 3000000.0, date3);

    public void setupStage1() {orderList = new OrderList();}

    public void setupStage2() {
        orderList = new OrderList();
        orderList.getOrders().add(order1);
        orderList.getOrders().add(order2);
    }

    public void setupStage3() {
        orderList = new OrderList();
        //orderList.getOrders().add(order1);
       // orderList.getOrders().add(order2);
     //   orderList.getOrders().add(order3);
    }

    // FALTANTES: En el añadir, falta tener en cuenta la cantidad de cada producto
    // La fecha de la orden, creo que debe ser manual, no automática

    @Test
    public void addOrderTest() {
        // Arrange
        setupStage1();
        // Act
       // orderList.getOrders().add(order2);
        // Assert
        assertEquals(orderList.getOrders().size(), 1);
    }

    @Test
    public void addOrderNegativeTotalPriceTest() {
        // Arrange
        setupStage1();
        // Act - Assert
        assertThrows(NegativeNumberException.class, () -> {
        //    orderList.getOrders().add(new Order("James", "Camiseta, Pantaloneta, Guayos", -250000.0, date2));
        });
        assertEquals(orderList.getOrders().size(), 0);
    }

    // El del formato no se valida, ya que debe ser automático

    @Test
    public void searchOrderByBuyerName() {
        // Arrange
        setupStage3();

        // Act - Assert
        assertEquals(orderList.searchOrder(1, "James"), "Buyer: James Products list: Camiseta, Pantaloneta, Guayos Total price: 250000.0 Purchase date: 2023-04-12");
    }

    @Test
    public void searchOrderByNameNotFoundTest() {
        // Arrange
        setupStage3();

        // Act - Assert
        assertThrows(IncompleteDataException.class, () -> {
            orderList.searchOrder(1, "");
        });
    }

    @Test
    public void searchOrderByTotalPrice() {
        // Arrange
        setupStage3();

        // Act - Assert
        assertEquals(orderList.searchOrder(2, "2000000.0"), "Buyer: Santiago Products list: PS5, PSP, Audífonos Total price: 2000000.0 Purchase date: 2023-04-05");
    }

    @Test
    public void searchOrderByNegativeTotalPrice() {
        // Arrange
        setupStage3();

        // Act - Assert
        assertThrows(NegativeNumberException.class, () -> {
            orderList.searchOrder(2, "-2000000.0");
        });
    }

    @Test
    public void searchOrderByDate() {
        // Arrange
        setupStage3();

        // Act - Assert
        assertEquals(orderList.searchOrder(3, "2023-04-05"), "");
    }

    @Test
    public void searchOrderByWrongDate() {
        // Arrange
        setupStage3();

        // Act - Assert
        assertThrows(DateFormatException.class, () -> {
            orderList.searchOrder(3, "2023");
        });
    }

}*/
