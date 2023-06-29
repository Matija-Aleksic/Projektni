package Iznimke;

public class UserNepostojiexception extends Exception {
    public UserNepostojiexception() {
    }

    public UserNepostojiexception(String message) {
        super(message);
    }

    public UserNepostojiexception(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNepostojiexception(Throwable cause) {
        super(cause);
    }

    public UserNepostojiexception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
