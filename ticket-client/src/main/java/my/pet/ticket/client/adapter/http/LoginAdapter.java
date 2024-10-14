package my.pet.ticket.client.adapter.http;

import io.grpc.StatusRuntimeException;
import my.pet.ticket.application.domain.model.payload.request.LoginRequest;
import my.pet.ticket.client.adapter.http.exception.LoginAdapterException;
import my.pet.ticket.client.application.domain.service.LoginService;
import my.pet.ticket.client.application.port.entrypoint.LoginPort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class LoginAdapter implements LoginPort {

  private final LoginService loginService;

  public LoginAdapter(LoginService loginService) {
    this.loginService = loginService;
  }

  @Override
  public String login(Model model, Boolean failure, Boolean logout) {
    try {
      return this.loginService.login(model, failure, logout);
    } catch (StatusRuntimeException e) {
      throw new LoginAdapterException(e);
    }
  }

  @Override
  public ResponseEntity<String> loginRequest(LoginRequest loginRequest) {
    try {
      return this.loginService.loginRequest(loginRequest);
    } catch (StatusRuntimeException e) {
      throw new LoginAdapterException(e);
    }
  }

}
