package my.pet.ticket.client.adapter.http;

import io.grpc.StatusRuntimeException;
import my.pet.ticket.application.domain.model.payload.request.RegisterUserRequest;
import my.pet.ticket.client.adapter.http.exception.RegistrationAdapterException;
import my.pet.ticket.client.application.domain.service.UserService;
import my.pet.ticket.client.application.port.entrypoint.RegistrationPort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class RegistrationAdapter implements RegistrationPort {

  private final UserService userService;

  public RegistrationAdapter(UserService userService) {
    this.userService = userService;
  }

  @Override
  public String register(Model model, Boolean failure) {
    return "registration.html";
  }

  public ResponseEntity<String> registrationRequest(RegisterUserRequest registerUserRequest) {
    try {
      return ResponseEntity.ok("");
    } catch (StatusRuntimeException e) {
      throw new RegistrationAdapterException(e);
    }
  }

}
