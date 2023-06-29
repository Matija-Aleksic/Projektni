package Iznimke;

public class NijeMoguceSpajanjeNaBazuRuntimeException extends RuntimeException {
    public NijeMoguceSpajanjeNaBazuRuntimeException() {
    }

    public NijeMoguceSpajanjeNaBazuRuntimeException(String message) {
        super(message);
    }

    public NijeMoguceSpajanjeNaBazuRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NijeMoguceSpajanjeNaBazuRuntimeException(Throwable cause) {
        super(cause);
    }

    public NijeMoguceSpajanjeNaBazuRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
