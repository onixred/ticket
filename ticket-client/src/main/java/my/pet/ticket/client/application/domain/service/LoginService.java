package my.pet.ticket.client.application.domain.service;

import my.pet.ticket.application.domain.model.Token;
import my.pet.ticket.application.domain.model.User;
import my.pet.ticket.application.domain.model.payload.request.LoginRequest;
import my.pet.ticket.client.application.port.api.LoginGrpcPort;
import my.pet.ticket.client.application.port.api.UserGrpcPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class LoginService {

  private final LoginGrpcPort loginGrpcPort;

  private final UserGrpcPort userGrpcPort;

  public LoginService(LoginGrpcPort loginGrpcPort, UserGrpcPort userGrpcPort) {
    this.loginGrpcPort = loginGrpcPort;
    this.userGrpcPort = userGrpcPort;
  }

  public String login(Model model, Boolean failure, Boolean logout) {
    model.addAttribute("error", failure);
    return "login.html";
  }

  public ResponseEntity<String> loginRequest(LoginRequest request) {
    Token token = this.loginGrpcPort.login(request);
    User user = this.userGrpcPort.getUser(token.getUserId(), token.getToken());
    return ResponseEntity.status(302).header(HttpHeaders.LOCATION, "/personal-account")
        .header(HttpHeaders.SET_COOKIE, "Authorization=" + token.getToken() + ";max-age=3600")
        .header(HttpHeaders.SET_COOKIE, "UserId=" + token.getUserId() + ";max-age=3600")
        .header(HttpHeaders.SET_COOKIE, "Role=" + user.getRole().getName() + ";max-age=3600")
        .build();
  }

}
