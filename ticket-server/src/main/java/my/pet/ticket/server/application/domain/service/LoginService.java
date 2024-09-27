package my.pet.ticket.server.application.domain.service;

import java.util.function.Function;
import my.pet.ticket.server.application.domain.model.Token;
import my.pet.ticket.server.application.domain.model.User;
import my.pet.ticket.server.application.domain.model.payload.request.LoginRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

  private static final DomainServiceException INCORRECT_PASSWORD = new DomainServiceException(
      "Incorrect password");

  private final UserService userService;

  private final TokenService tokenService;

  private final ModelMapper modelMapper;

  public LoginService(UserService userService, TokenService tokenService, ModelMapper modelMapper) {
    this.userService = userService;
    this.tokenService = tokenService;
    this.modelMapper = modelMapper;
  }

  public Token login(Function<LoginRequest.LoginRequestBuilder, LoginRequest> loginRequest) {
    return login(loginRequest.apply(LoginRequest.builder()));
  }

  public Token login(LoginRequest loginRequest) {
    User user = this.userService.getByLogin(loginRequest.getLogin());
    if (user.getPassword().equals(loginRequest.getPassword())) {
      return this.modelMapper.map(this.tokenService.createToken(user.getUserId()), Token.class);
    }
    throw INCORRECT_PASSWORD;
  }

}
