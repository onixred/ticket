package my.pet.ticket.client.adapter.http;

import my.pet.ticket.client.application.domain.model.User;
import my.pet.ticket.client.application.domain.service.UserService;
import my.pet.ticket.client.application.port.entrypoint.CabinetPort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class CabinetAdapter implements CabinetPort {

  private final UserService userService;

  public CabinetAdapter(UserService userService) {
    this.userService = userService;
  }

  @Override
  public String cabinet(Model model, String token, Long userId, String role) {
    model.addAttribute("role", role);
    User user = this.userService.getUser(userId, token);
    model.addAttribute("user", user);
    return "cabinet.html";
  }

}
