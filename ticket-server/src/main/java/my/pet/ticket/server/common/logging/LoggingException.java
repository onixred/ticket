package my.pet.ticket.server.common.logging;

import my.pet.ticket.server.common.exception.AbstractRuntimeException;

public class LoggingException
        extends AbstractRuntimeException {

    public LoggingException (String message) {
        super(message);
    }

    public LoggingException (Throwable cause) {
        super(cause);
    }

    public LoggingException (String message, Throwable cause) {
        super(message, cause);
    }

}
