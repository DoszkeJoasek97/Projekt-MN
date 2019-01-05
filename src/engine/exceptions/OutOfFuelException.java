package engine.exceptions;

public class OutOfFuelException extends Exception {

    //TODO wyrzuci się go i obsłuży jak skończy się paliwo

    public OutOfFuelException() {
    }

    public OutOfFuelException(String message) {
        super(message);
    }

    public OutOfFuelException(String message, Throwable cause) {
        super(message, cause);
    }

    public OutOfFuelException(Throwable cause) {
        super(cause);
    }

    public OutOfFuelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
