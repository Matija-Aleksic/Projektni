package Iznimke;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NepotpunUnosException extends Exception{
    Logger logger = LoggerFactory.getLogger(NepotpunUnosException.class);

    public NepotpunUnosException(String message) {
        super(message);
        logger.info("dogodio se nepotpun unos");
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
