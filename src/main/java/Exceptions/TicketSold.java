package Exceptions;

public class TicketSold extends RuntimeException {
    public TicketSold() {
        super();
    }

    public TicketSold(String message) {
        super(message);
    }

    public TicketSold(String message, Throwable cause) {
        super(message, cause);
    }

    public TicketSold(Throwable cause) {
        super(cause);
    }

    protected TicketSold(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
