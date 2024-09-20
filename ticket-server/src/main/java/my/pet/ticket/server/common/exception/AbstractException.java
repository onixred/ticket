package my.pet.ticket.server.common.exception;

public abstract class AbstractException
        extends Exception {

    public AbstractException (String message) {
        super(message);
    }

    public AbstractException (Throwable cause) {
        super(cause);
    }

    public AbstractException (String message, Throwable cause) {
        super(message, cause);
    }

}
