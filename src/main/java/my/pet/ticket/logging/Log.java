package my.pet.ticket.logging;

import my.pet.ticket.controller.TicketController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Log class for
 *
 * @author <a href="mailto:baranov.alexalex@gmail.com">abaranov</a>
 */
public class Log {

    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);


    public static void DEBUG(String message, Event event, Detail... details) {
        logger.debug(message + " " + event.getDescription(), details);
    }

    public static void INFO(String message, Event event, Detail... details){
        logger.info(message + " " + event.getDescription(), details);
    }

    public static void WARN(String message, Event event, Detail... details){
        logger.warn(message + " " + event.getErrorCode(), details);
    }

    public static void WARN(String message, Event event, Throwable causae, Detail... details){
        logger.warn(message + " " + event.getErrorCode(), causae, details);
    }

    public static void ERROR(String message, Event event, Detail... details){
        logger.error(message + " " + event.getErrorCode(), details);
    }

    public static void ERROR(String message, Event event, Throwable causae, Detail... details){
        logger.error(message + " " + event.getErrorCode(), causae, details);
    }
}
