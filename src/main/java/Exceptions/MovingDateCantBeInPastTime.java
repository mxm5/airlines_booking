package Exceptions;

public class MovingDateCantBeInPastTime extends RuntimeException {
    public MovingDateCantBeInPastTime() {
        super();
    }

    public MovingDateCantBeInPastTime(String message) {
        super(message);
    }

    public MovingDateCantBeInPastTime(String message, Throwable cause) {
        super(message, cause);
    }

    public MovingDateCantBeInPastTime(Throwable cause) {
        super(cause);
    }

    protected MovingDateCantBeInPastTime(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
