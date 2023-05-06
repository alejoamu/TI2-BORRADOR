package model;

import exceptions.NegativeNumberException;
import exceptions.QuantityExceededException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    Product product = null;

    public void setupStage1(){
        product = new Product("PS5", "Videogame", 2000000.0, 4, Category.TOYS_AND_GAMES, 2);
    }

    @Test
    public void ProductTest() {
        //Act
        Product product = new Product("PS5", "Videogame", 2000000.0, 4, Category.TOYS_AND_GAMES, 2);

        //Assert
        assertEquals(product.toString(), "PS5 Videogame 2000000.0 4 TOYS_AND_GAMES 2");
    }

    @Test
    public void addQuantityAvailable(){
        //Arrange
        setupStage1();

        //Act
        product.addQuantityAvailable(4);

        //Assert
        assertEquals(product.getQuantityAvailable(), 8);
    }

    @Test
    public void addNegativeQuantityAvailable(){
        //Arrange
        setupStage1();

        //Act - Assert
        assertThrows(NegativeNumberException.class, () ->{
            product.addQuantityAvailable(-4);
        });
    }

    @Test
    public void subtractQuantityAvailable(){
        //Arrange
        setupStage1();

        //Act
        product.subtractQuantityAvailable(4);

        //Assert
        assertEquals(product.getQuantityAvailable(), 0);
    }

    @Test
    public void subtractNegativeQuantityAvailable(){
        //Arrange
        setupStage1();

        //Act - Assert
        assertThrows(NegativeNumberException.class, () ->{
            product.subtractQuantityAvailable(-4);
        });
    }

    @Test
    public void subtractMoreThanAllowedQuantity() {
        // Arrange
        setupStage1();

        // Act - Assert
        assertThrows(QuantityExceededException.class, () -> {
            product.subtractQuantityAvailable(7);
        });
    }



}
