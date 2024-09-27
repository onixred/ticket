package my.pet.ticket.client.adapter.http.exception;

public class LoginAdapterException extends RuntimeException {

  public LoginAdapterException(Throwable cause) {
    super(cause);
  }

  public LoginAdapterException(String message, Throwable cause) {
    super(message, cause);
  }

}
