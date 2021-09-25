package Exceptions;

public class TransactionIsOpen extends RuntimeException{
    public TransactionIsOpen() {
        super();
    }

    public TransactionIsOpen(String message) {
        super(message);
    }

    public TransactionIsOpen(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionIsOpen(Throwable cause) {
        super(cause);
    }

    protected TransactionIsOpen(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
