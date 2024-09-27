package my.pet.ticket.client.adapter.http;

import my.pet.ticket.client.application.domain.model.Token;
import my.pet.ticket.client.application.domain.model.User;
import my.pet.ticket.client.application.domain.model.payload.request.LoginRequest;
import my.pet.ticket.client.application.domain.service.LoginService;
import my.pet.ticket.client.application.domain.service.UserService;
import my.pet.ticket.client.application.port.entrypoint.LoginPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class LoginAdapter implements LoginPort {

  private final LoginService loginService;

  private final UserService userService;

  public LoginAdapter(LoginService loginService, UserService userService) {
    this.loginService = loginService;
    this.userService = userService;
  }

  @Override
  public String login(Model model, Boolean failure, Boolean logout) {
    model.addAttribute("error", failure);
    return "login.html";
  }

  @Override
  public ResponseEntity<String> loginRequest(Model model, LoginRequest loginRequest) {
    Token token = this.loginService.login(loginRequest);
    User user = this.userService.getUser(token.getUserId(), token.getToken());
    return ResponseEntity.status(302).header(HttpHeaders.LOCATION, "/cabinet")
        .header(HttpHeaders.SET_COOKIE, "Authorization=" + token.getToken() + ";max-age=3600")
        .header(HttpHeaders.SET_COOKIE, "UserId=" + token.getUserId() + ";max-age=3600")
        .header(HttpHeaders.SET_COOKIE, "Role=" + user.getRole().getName() + ";max-age=3600")
        .build();
  }

}
