package my.pet.ticket.logging;

import java.util.Arrays;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Log {

    private static final Logger LOGGER = LoggerFactory.getLogger(Log.class);

    private static final String MESSAGE_DETAILS_TEMPLATE = "Message: '{}'. Details: '{}'.";

    private static final String MESSAGE_EVENT_DETAILS_TEMPLATE = "Message: '{}'. Event: '{}'. Details: '{}'.";

    private static final String MESSAGE_EVENT_EXCEPTION_DETAILS_TEMPLATE = "Message: '{}'. Event: '{}'. Exception: '{}'. Details: '{}'.";

    public static void DEBUG(String message, Detail... details) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(MESSAGE_DETAILS_TEMPLATE, message, format(details));
        }
    }

    public static void DEBUG(String message, Event event, Detail... details) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(MESSAGE_EVENT_DETAILS_TEMPLATE, message, event.getDescription(), format(details));
        }
    }

    public static void INFO(String message, Detail... details) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(MESSAGE_DETAILS_TEMPLATE, message, format(details));
        }
    }

    public static void INFO(String message, Event event, Detail... details) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(MESSAGE_EVENT_DETAILS_TEMPLATE, message, event.getDescription(), format(details));
        }
    }

    public static void WARN(String message, Detail... details) {
        if (LOGGER.isWarnEnabled()) {
            LOGGER.warn(MESSAGE_DETAILS_TEMPLATE, message, format(details));
        }
    }

    public static void WARN(String message, Event event, Detail... details) {
        if (LOGGER.isWarnEnabled()) {
            LOGGER.warn(MESSAGE_EVENT_DETAILS_TEMPLATE, message, event.getErrorCode(), format(details));
        }
    }

    public static void WARN(String message, Event event, Throwable cause, Detail... details) {
        if (LOGGER.isWarnEnabled()) {
            LOGGER.warn(MESSAGE_EVENT_EXCEPTION_DETAILS_TEMPLATE, message, event.getErrorCode(), cause, format(details));
        }
    }

    public static void ERROR(String message, Detail... details) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error(MESSAGE_DETAILS_TEMPLATE, message, format(details));
        }
    }

    public static void ERROR(String message, Event event, Detail... details) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error(MESSAGE_EVENT_DETAILS_TEMPLATE, message, event.getErrorCode(), format(details));
        }
    }

    public static void ERROR(String message, Event event, Throwable cause, Detail... details) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error(MESSAGE_EVENT_EXCEPTION_DETAILS_TEMPLATE, message, event.getErrorCode(), cause, format(details));
        }
    }

    private static String format(Detail... details) {
        return Arrays.stream(details)
                .map(detail -> detail.toDetails().toString())
                .reduce("", (result, product) -> String.join(", ", result, product));
    }

}
