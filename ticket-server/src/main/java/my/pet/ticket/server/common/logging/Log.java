package my.pet.ticket.server.common.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

public class Log {

    private static final Logger LOGGER = LoggerFactory.getLogger(Log.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final String LOG_PATTERN_MESSAGE_DETAIL = "Message: {0} | Details: {1}";

    private static final String LOG_PATTERN_MESSAGE_EVENT_DETAIL = "Message: {0} | Event: {1} | Details: {2}";

    private static final String LOG_PATTERN_MESSAGE_EVENT_CAUSE_DETAIL = "Message: {0} | Event: {1} | Cause: {2} | Details: {3}";

    public static void DEBUG(String message, Detail... details) {
        LOGGER.debug(writeLogToString(message, details));
    }

    public static void DEBUG(String message, Event event, Detail... details) {
        LOGGER.debug(writeLogToString(message, event, details));
    }

    public static void INFO(String message, Detail... details) {
        LOGGER.info(writeLogToString(message, details));
    }

    public static void INFO(String message, Event event, Detail... details) {
        LOGGER.info(writeLogToString(message, event, details));
    }

    public static void WARN(String message, Detail... details) {
        LOGGER.warn(writeLogToString(message, details));
    }

    public static void WARN(String message, Event event, Detail... details) {
        LOGGER.warn(writeLogToString(message, event, details));
    }

    public static void WARN(String message, Event event, Throwable causae, Detail... details) {
        LOGGER.warn(writeLogToString(message, event, causae, details));
    }

    public static void ERROR(String message, Detail... details) {
        LOGGER.error(writeLogToString(message, details));
    }

    public static void ERROR(String message, Event event, Detail... details) {
        LOGGER.error(writeLogToString(message, event, details));
    }

    public static void ERROR(String message, Event event, Throwable causae, Detail... details) {
        LOGGER.error(writeLogToString(message, event, causae, details));
    }

    public static String writeLogToString(String message, Detail... details) {
        return MessageFormat.format(
                LOG_PATTERN_MESSAGE_DETAIL,
                message,
                writeDetailsToString(details)
        );
    }

    public static String writeLogToString(String message, Event event, Detail... details) {
        return MessageFormat.format(
                LOG_PATTERN_MESSAGE_EVENT_DETAIL,
                message,
                writeEventToString(event),
                writeDetailsToString(details)
        );
    }

    public static String writeLogToString(String message, Event event, Throwable cause, Detail... details) {
        return MessageFormat.format(
                LOG_PATTERN_MESSAGE_EVENT_CAUSE_DETAIL,
                message,
                writeEventToString(event),
                cause != null ? cause.getMessage() : "null",
                writeDetailsToString(details)
        );
    }

    private static String writeEventToString(Event event) {
        if(event == null) return "null";
        try {
            return OBJECT_MAPPER.writeValueAsString(event.toDetails());
        } catch (JsonProcessingException e) {
            throw new LoggingException("Json processing error", e);
        }
    }

    private static String writeDetailsToString(Detail... details) {
        if(details == null) return "null";
        try {
            return OBJECT_MAPPER.writeValueAsString(details);
        } catch (JsonProcessingException e) {
            throw new LoggingException("Json processing error", e);
        }
    }

}
