package my.pet.ticket.server.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.boot.logging.LogLevel;

import java.text.MessageFormat;
import java.util.stream.Stream;

public class Log {

    private static final Logger LOGGER = LoggerFactory.getLogger(Log.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final String LOG_PATTERN_MESSAGE_DETAIL = "Message: {0} | Details: {1}";

    private static final String LOG_PATTERN_MESSAGE_EVENT_DETAIL = "Message: {0} | Event: {1} | Details: {2}";

    private static final String LOG_PATTERN_MESSAGE_EVENT_CAUSE_DETAIL = "Message: {0} | Event: {1} | Cause: {2} | Details: {3}";

    public static void DEBUG(String message, Detail... details) {
        log(Level.DEBUG, message, details);
    }

    public static void DEBUG(String message, Event event, Detail... details) {
        log(Level.DEBUG, message, event, details);
    }

    public static void INFO(String message, Detail... details) {
        log(Level.INFO, message, details);
    }

    public static void INFO(String message, Event event, Detail... details) {
        log(Level.INFO, message, event, details);
    }

    public static void WARN(String message, Detail... details) {
        log(Level.WARN, message, details);
    }

    public static void WARN(String message, Event event, Detail... details) {
        log(Level.WARN, message, event, details);
    }

    public static void WARN(String message, Event event, Throwable causae, Detail... details) {
        log(Level.WARN, message, event, causae, details);
    }

    public static void ERROR(String message, Detail... details) {
        log(Level.ERROR, message, details);
    }

    public static void ERROR(String message, Event event, Detail... details) {
        log(Level.ERROR, message, event, details);
    }

    public static void ERROR(String message, Event event, Throwable causae, Detail... details) {
        log(Level.ERROR, message, event, causae, details);
    }

    private static void log(Level level, String message, Event event, Throwable cause, Detail... details) {
        LOGGER.atLevel(level).log(MessageFormat.format(LOG_PATTERN_MESSAGE_EVENT_CAUSE_DETAIL, message, writeEventToString(event), cause.getMessage(), writeDetailsToString(details)));
    }

    private static void log(Level level, String message, Event event, Detail... details) {
        LOGGER.atLevel(level).log(MessageFormat.format(LOG_PATTERN_MESSAGE_EVENT_DETAIL, message, writeEventToString(event), writeDetailsToString(details)));
    }

    private static void log(Level level, String message, Detail... details) {
        LOGGER.atLevel(level).log(MessageFormat.format(LOG_PATTERN_MESSAGE_DETAIL, message, writeDetailsToString(details)));
    }

    private static String writeEventToString(Event event) {
        try {
            return OBJECT_MAPPER.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static String writeDetailsToString(Detail... details) {
        try {
            return OBJECT_MAPPER.writeValueAsString(details);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
