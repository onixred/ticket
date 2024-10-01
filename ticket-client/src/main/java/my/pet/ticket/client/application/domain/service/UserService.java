package my.pet.ticket.client.application.domain.service;

import java.util.List;
import my.pet.ticket.application.domain.model.User;
import my.pet.ticket.client.application.port.api.UserGrpcPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UserService {

  private final UserGrpcPort userGrpcPort;

  public UserService(UserGrpcPort userGrpcPort) {
    this.userGrpcPort = userGrpcPort;
  }

  public String user(Model model, String token, Long userId, String role, Long requestUserId) {
    List<User> users = this.userGrpcPort.getAllUsers(token);
    model.addAttribute("users", users);
    if (requestUserId != null) {
      users.stream().filter(user -> user.getUserId().equals(requestUserId)).findFirst()
          .ifPresent(user -> model.addAttribute("requestUser", user));
    }
    return "users.html";
  }

  public ResponseEntity<String> registrationUserRequest() {

    return ResponseEntity.status(302).header(HttpHeaders.LOCATION, "/login")
        .build();
  }

}
