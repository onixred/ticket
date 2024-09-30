package my.pet.ticket.client.application.domain.service;

import java.util.List;
import my.pet.ticket.client.application.domain.model.User;
import my.pet.ticket.client.application.port.api.UserGrpcPort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UserService {

  private final UserGrpcPort userGrpcPort;

  public UserService(UserGrpcPort userGrpcPort) {
    this.userGrpcPort = userGrpcPort;
  }

  public String user(Model model, String token, Long userId, String role) {
    List<User> users = this.userGrpcPort.getAllUsers(token);
    model.addAttribute("users", users);
    return "users.html";
  }

}
