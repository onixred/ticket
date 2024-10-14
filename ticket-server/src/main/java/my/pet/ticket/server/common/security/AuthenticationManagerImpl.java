package my.pet.ticket.server.common.security;

import java.util.List;
import my.pet.ticket.application.domain.model.Token;
import my.pet.ticket.application.domain.model.User;
import my.pet.ticket.server.application.domain.service.TokenService;
import my.pet.ticket.server.application.domain.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationManagerImpl implements AuthenticationManager {

  private final TokenService tokenService;

  private final UserService userService;

  public AuthenticationManagerImpl(TokenService tokenService, UserService userService) {
    this.tokenService = tokenService;
    this.userService = userService;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    if (authentication != null && authentication.getPrincipal() != null) {
      Token token = this.tokenService.getTokenByToken((String) authentication.getPrincipal());
      User user = this.userService.get(token.getUserId());
      BearerAuthenticationToken bearerAuthenticationToken = new BearerAuthenticationToken(
          token.getUserId(), token.getToken(), List.of(new GrantedAuthorityImpl(user.getRole())));
      bearerAuthenticationToken.setAuthenticated(true);
      return bearerAuthenticationToken;
    }
    throw new BadCredentialsException("1");
  }

}
