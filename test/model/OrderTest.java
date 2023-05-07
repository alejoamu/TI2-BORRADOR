package model;


import exceptions.NegativeNumberException;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderTest {

    LocalDate date = LocalDate.of(2023, 4, 5);
    String[] products1 = {"PS5", "PSP", "Audífonos"};

    String[] productsQuantity1 = {"2", "3", "2"};


    @Test
    public void OrderTest() {
        //Act
        Order order = new Order("Santiago", products1, productsQuantity1,2000000, date);

        //Assert
        assertEquals(order.toString(), "Santiago [PS5, PSP, Audífonos] [2, 3, 2] 2000000.0 2023-04-05");
    }

    @Test
    public void NegativePriceTest() {
        //Act - Assert
        assertThrows(NegativeNumberException.class, () -> {
            Order order = new Order("Santiago", products1, productsQuantity1,-2000000, date);
        });
    }

}
