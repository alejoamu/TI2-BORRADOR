package exceptions;

public class QuantityExceededException extends RuntimeException{
    public QuantityExceededException(){super("Quantity to carry is greater than quantity in stock");}
}
