package Loger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
    static Logger logger = LoggerFactory.getLogger(Log.class);
    public static void info(String a){
        logger.info(a);
    }
    public static void error(String a){
        logger.error(a);
    }
}
