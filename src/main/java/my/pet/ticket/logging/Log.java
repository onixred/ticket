package my.pet.ticket.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
    private static final Logger logger = LoggerFactory.getLogger(Log.class);

    public static void DEBUG(String message, Detail... details) {
        logger.debug(message, details);
    }

    public static void DEBUG(String message, Event event, Detail... details) {
        logger.debug(message + " [Event: {}]", event, details);
    }

    public static void INFO(String message, Detail... details) {
        logger.info(message, details);
    }

    public static void INFO(String message, Event event, Detail... details) {
        logger.info(message + " [Event: {}]", event, details);
    }

    public static void WARN(String message, Detail... details) {
        logger.warn(message, details);
    }

    public static void WARN(String message, Event event, Detail... details) {
        logger.warn(message + " [Event: {}]", event, details);
    }

    public static void WARN(String message, Event event, Throwable cause, Detail... details) {
        logger.warn(message + " [Event: {}] [Cause: {}]", event, cause, details);
    }

    public static void ERROR(String message, Detail... details) {
        logger.error(message, details);
    }

    public static void ERROR(String message, Event event, Detail... details) {
        logger.error(message + " [Event: {}]", event, details);
    }

    public static void ERROR(String message, Event event, Throwable cause, Detail... details) {
        logger.error(message + " [Event: {}] [Cause: {}]", event, cause, details);
    }
}
