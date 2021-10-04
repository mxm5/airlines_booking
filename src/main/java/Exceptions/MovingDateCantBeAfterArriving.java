package Exceptions;

public class MovingDateCantBeAfterArriving extends RuntimeException {
    public MovingDateCantBeAfterArriving() {
        super();
    }

    public MovingDateCantBeAfterArriving(String message) {
        super(message);
    }

    public MovingDateCantBeAfterArriving(String message, Throwable cause) {
        super(message, cause);
    }

    public MovingDateCantBeAfterArriving(Throwable cause) {
        super(cause);
    }

    protected MovingDateCantBeAfterArriving(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
