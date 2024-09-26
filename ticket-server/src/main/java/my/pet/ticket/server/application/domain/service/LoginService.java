package my.pet.ticket.server.application.domain.service;

import java.util.function.Function;
import my.pet.ticket.server.application.domain.model.User;
import my.pet.ticket.server.application.domain.model.payload.request.LoginRequest;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

  private static final DomainServiceException INCORRECT_PASSWORD = new DomainServiceException(
      "Incorrect password");

  private final UserService userService;

  private final TokenService tokenService;

  public LoginService(UserService userService, TokenService tokenService) {
    this.userService = userService;
    this.tokenService = tokenService;
  }

  public String login(Function<LoginRequest.LoginRequestBuilder, LoginRequest> loginRequest) {
    return login(loginRequest.apply(LoginRequest.builder()));
  }

  public String login(LoginRequest loginRequest) {
    User user = this.userService.getByLogin(loginRequest.getLogin());
    if (user.getPassword().equals(loginRequest.getPassword())) {
      return this.tokenService.createToken(user.getUserId()).getToken();
    }
    throw INCORRECT_PASSWORD;
  }

}
