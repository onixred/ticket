package my.pet.ticket.client.adapter.http.exception;

public class RegistrationAdapterException extends RuntimeException {

  public RegistrationAdapterException(String message) {
    super(message);
  }

  public RegistrationAdapterException(Throwable e) {
    super(e);
  }

  public RegistrationAdapterException(String message, Throwable e) {
    super(message, e);
  }

}
