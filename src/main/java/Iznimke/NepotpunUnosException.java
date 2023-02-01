package Iznimke;

public class NepotpunUnosException extends Exception{

    public NepotpunUnosException(String message) {
        super(message);
    }

    public NepotpunUnosException(String message, Throwable cause) {
        super(message, cause);
    }

    public NepotpunUnosException(Throwable cause) {
        super(cause);
    }

    public NepotpunUnosException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
