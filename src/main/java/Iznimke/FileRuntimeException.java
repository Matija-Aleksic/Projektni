package Iznimke;

public class FileRuntimeException extends RuntimeException{
    public FileRuntimeException() {
    }

    public FileRuntimeException(String message) {
        super(message);
    }

    public FileRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileRuntimeException(Throwable cause) {
        super(cause);
    }

    public FileRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
