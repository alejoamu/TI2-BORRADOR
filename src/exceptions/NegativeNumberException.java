package exceptions;

public class NegativeNumberException extends RuntimeException {
    public NegativeNumberException() {
        super("              NEGATIVE NUMBERS CANNOT BE ENTERED               ");
    }
}
