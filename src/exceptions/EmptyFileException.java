package exceptions;

public class EmptyFileException extends RuntimeException {
    public EmptyFileException() {
        super("                      THE FILE IS EMPTY                        ");
    }
}
