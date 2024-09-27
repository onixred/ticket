package my.pet.ticket.client.application.domain.service;

import my.pet.ticket.client.application.domain.model.User;
import my.pet.ticket.client.application.port.api.UserGrpcPort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class CabinetService {

  private final UserGrpcPort userGrpcPort;

  public CabinetService(UserGrpcPort userGrpcPort) {
    this.userGrpcPort = userGrpcPort;
  }

  public String cabinet(Model model, String token, Long userId, String role) {
    model.addAttribute("role", role);
    User user = this.userGrpcPort.getUser(userId, token);
    model.addAttribute("user", user);
    return "cabinet.html";
  }

}
