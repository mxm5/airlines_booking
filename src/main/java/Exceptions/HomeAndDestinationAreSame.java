package Exceptions;

public class HomeAndDestinationAreSame extends RuntimeException {
    public HomeAndDestinationAreSame() {
        super();
    }

    public HomeAndDestinationAreSame(String message) {
        super(message);
    }

    public HomeAndDestinationAreSame(String message, Throwable cause) {
        super(message, cause);
    }

    public HomeAndDestinationAreSame(Throwable cause) {
        super(cause);
    }

    protected HomeAndDestinationAreSame(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
