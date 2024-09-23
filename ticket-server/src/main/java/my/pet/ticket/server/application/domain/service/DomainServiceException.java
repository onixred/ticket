package my.pet.ticket.server.application.domain.service;

import my.pet.ticket.server.common.exception.AbstractRuntimeException;

public class DomainServiceException
    extends AbstractRuntimeException {

    public DomainServiceException(String message) {
        super(message);
    }

    public DomainServiceException(Throwable cause) {
        super(cause);
    }

    public DomainServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
