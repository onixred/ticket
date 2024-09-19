package my.pet.ticket.server.adapter.persistence;

import my.pet.ticket.server.common.exception.AbstractRuntimeException;

public class PersistenceAdapterException extends AbstractRuntimeException {

    public PersistenceAdapterException(String message) {
        super(message);
    }

    public PersistenceAdapterException(Throwable cause) {
        super(cause);
    }

    public PersistenceAdapterException(String message, Throwable cause) {
        super(message, cause);
    }

}
