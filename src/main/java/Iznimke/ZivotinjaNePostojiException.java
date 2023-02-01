package Iznimke;

public class ZivotinjaNePostojiException extends Exception{
    public ZivotinjaNePostojiException() {
    }

    public ZivotinjaNePostojiException(String message) {
        super(message);
    }

    public ZivotinjaNePostojiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZivotinjaNePostojiException(Throwable cause) {
        super(cause);
    }

    public ZivotinjaNePostojiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
