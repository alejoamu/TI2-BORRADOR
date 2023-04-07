package exceptions;

public class IncompleteDataException extends RuntimeException{
    public IncompleteDataException(){super("      DATA REQUIRED TO ADD ELEMENT WAS NOT PROVIDED      ");}
}
