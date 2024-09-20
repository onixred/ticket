package my.pet.ticket.server.adapter.etcd;

import my.pet.ticket.server.common.exception.AbstractRuntimeException;

public class EtcdAdapterException
        extends AbstractRuntimeException {

    public EtcdAdapterException (String message) {
        super(message);
    }

    public EtcdAdapterException (Throwable cause) {
        super(cause);
    }

    public EtcdAdapterException (String message, Throwable cause) {
        super(message, cause);
    }

}
