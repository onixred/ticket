package my.pet.ticket.server.common.exception;

public abstract class AbstractRuntimeException
    extends RuntimeException {

  public AbstractRuntimeException(String message) {
    super(message);
  }

  public AbstractRuntimeException(Throwable cause) {
    super(cause);
  }

  public AbstractRuntimeException(String message, Throwable cause) {
    super(message, cause);
  }

}
