package exceptions;

public class DateFormatException extends RuntimeException {
    public DateFormatException() {
        super("            DATE FORMAT ENTERED MUST BE YYYY-MM-DD             ");
    }
}
