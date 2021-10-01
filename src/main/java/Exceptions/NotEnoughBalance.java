package Exceptions;


public class NotEnoughBalance extends RuntimeException {
    public NotEnoughBalance() {
        super();
    }

    public NotEnoughBalance(String message) {
        super(message);
    }

    public NotEnoughBalance(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughBalance(Throwable cause) {
        super(cause);
    }

    protected NotEnoughBalance(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
