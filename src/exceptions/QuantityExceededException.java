package exceptions;

public class QuantityExceededException extends RuntimeException{
    public QuantityExceededException(){
        super("      QUANTITY TO CARRY IS GREATER THAN QUANTITY IN STOCK      ");
    }
}
