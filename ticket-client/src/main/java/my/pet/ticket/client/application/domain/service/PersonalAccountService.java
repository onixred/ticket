package my.pet.ticket.client.application.domain.service;

import java.util.Collections;
import my.pet.ticket.client.application.domain.model.User;
import my.pet.ticket.client.application.port.api.UserGrpcPort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class PersonalAccountService {

  private final UserGrpcPort userGrpcPort;

  public PersonalAccountService(UserGrpcPort userGrpcPort) {
    this.userGrpcPort = userGrpcPort;
  }

  public String personalAccount(Model model, String token, Long userId, String role) {
    model.addAttribute("role", role);
    User user = this.userGrpcPort.getUser(userId, token);
    model.addAttribute("user", user);
    model.addAttribute("activeTickets", Collections.emptyList());
    return "personal-account.html";
  }

}
