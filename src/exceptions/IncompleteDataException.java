package exceptions;

public class IncompleteDataException extends RuntimeException {
    public IncompleteDataException() {
        super("                REQUIRED DATA WAS NOT PROVIDED                 ");
    }
}
