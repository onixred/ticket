package my.pet.ticket.server.adapter.http;

import my.pet.ticket.server.application.domain.model.User;
import my.pet.ticket.server.application.domain.model.payload.request.LoginRequest;
import my.pet.ticket.server.application.port.entrypoint.EntrypointPort;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpAdapter
        implements EntrypointPort {


  @Override
  public User login(LoginRequest request) {
    return null;
  }

  @Override
  public void logout() {

  }
}
