package my.pet.ticket.client.adapter.http;

import io.grpc.StatusRuntimeException;
import my.pet.ticket.client.adapter.http.exception.UserAdapterException;
import my.pet.ticket.client.application.domain.service.UserService;
import my.pet.ticket.client.application.port.entrypoint.UsersPort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class UsersAdapter implements UsersPort {

  private final UserService userService;

  public UsersAdapter(UserService userService) {
    this.userService = userService;
  }

  @Override
  public String index(Model model, String token, Long userId, String role) {
    try {
      return this.userService.user(model, token, userId, role);
    } catch (StatusRuntimeException e) {
      throw new UserAdapterException(e);
    }
  }

}
