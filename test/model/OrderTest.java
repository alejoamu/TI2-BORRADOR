package model;


import exceptions.NegativeNumberException;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    LocalDate date = null;

    public void setupStage1(){
        date = LocalDate.of(2023, 04, 05);
    }

    @Test
    public void OrderTest(){
        //Arrange
        setupStage1();

        //Act
        Order order = new Order("Santiago", "PS5, PSP, AUDIFONOS", 2000000, date);

        //Assert
        assertEquals(order.toString(), "Santiago PS5, PSP, AUDIFONOS 2000000.0 2023-04-05");
    }

    @Test
    public void NegativePriceTest(){
        //Arrange
        setupStage1();

        //Act - Assert
        assertThrows(NegativeNumberException.class, ()->{
            Order order = new Order("Santiago", "PS5, PSP, AUDIFONOS", -2000000, date);
        });
    }

}
