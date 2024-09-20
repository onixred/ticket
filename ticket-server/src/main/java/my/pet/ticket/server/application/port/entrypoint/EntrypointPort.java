package my.pet.ticket.server.application.port.entrypoint;

import my.pet.ticket.server.application.domain.model.User;
import my.pet.ticket.server.application.domain.model.payload.request.LoginRequest;

public interface EntrypointPort {

  User login(LoginRequest request);

  void logout();

}
