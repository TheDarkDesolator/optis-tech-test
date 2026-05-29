package be.sbln.optis.vehicleevents.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingUtil {

    public static void logInfo(Object c, String message, Object o){
        log.info("{} - {}: {}", c.getClass().getSimpleName(), message, o);
    }
    public static void logDebug(Object c, String message, Object o){
        log.debug("{} - {}: {}", c.getClass().getSimpleName(), message, o);
    }
    public static void logWarn(Object c, String message, Object o){
        log.warn("{} - {}: {}", c.getClass().getSimpleName(), message, o);
    }
    public static void logError(Object c, String message, Object o){
        log.error("{} - {}: {}", c.getClass().getSimpleName(), message, o);
    }
}
