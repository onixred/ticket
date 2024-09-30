package my.pet.ticket.client.adapter.http.exception;

public class UserAdapterException extends RuntimeException {

  public UserAdapterException(String message) {
    super(message);
  }

  public UserAdapterException(Throwable cause) {
    super(cause);
  }

  public UserAdapterException(String message, Throwable cause) {
    super(message, cause);
  }

}
