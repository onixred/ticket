package my.pet.ticket.client.application.domain.service;

import my.pet.ticket.application.domain.model.Users;
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

  public String user(Model model, String token, Long userId, String role, Long requestUserId,
      Integer page) {
    Users users = this.userGrpcPort.getAllUsers(token, page, 4);
    model.addAttribute("users", users.getUsers());
    model.addAttribute("pages", users.getPages());
    model.addAttribute("page", page != null ? page : 0);
    if (requestUserId != null) {
      users.getUsers().stream().filter(user -> user.getUserId().equals(requestUserId)).findFirst()
          .ifPresent(user -> model.addAttribute("requestUser", user));
    }
    return "users.html";
  }

  public ResponseEntity<String> registrationUserRequest() {

    return ResponseEntity.status(302).header(HttpHeaders.LOCATION, "/login")
        .build();
  }

}
