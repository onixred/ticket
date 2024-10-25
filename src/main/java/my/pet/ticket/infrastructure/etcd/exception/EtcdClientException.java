package my.pet.ticket.infrastructure.etcd.exception;

public class EtcdClientException extends RuntimeException {

    public EtcdClientException(String message, Throwable cause) {
        super(message, cause);
    }

}
