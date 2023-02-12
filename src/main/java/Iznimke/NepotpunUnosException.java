package Iznimke;

import Loger.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NepotpunUnosException extends Exception{

    public NepotpunUnosException(String message) {
        super(message);
        Log.info("dogodio se nepotpun unos");
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
