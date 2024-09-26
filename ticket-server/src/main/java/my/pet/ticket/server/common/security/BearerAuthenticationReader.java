package my.pet.ticket.server.common.security;

import io.grpc.Metadata;
import io.grpc.Metadata.Key;
import io.grpc.ServerCall;
import java.util.List;
import javax.annotation.Nullable;
import my.pet.ticket.server.application.domain.model.Token;
import my.pet.ticket.server.application.domain.model.User;
import my.pet.ticket.server.application.domain.service.TokenService;
import my.pet.ticket.server.application.domain.service.UserService;
import net.devh.boot.grpc.server.security.authentication.GrpcAuthenticationReader;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class BearerAuthenticationReader implements GrpcAuthenticationReader {

  private final TokenService tokenService;

  private final UserService userService;

  public BearerAuthenticationReader(TokenService tokenService, UserService userService) {
    this.tokenService = tokenService;
    this.userService = userService;
  }

  @Nullable
  @Override
  public Authentication readAuthentication(ServerCall<?, ?> call, Metadata headers)
      throws AuthenticationException {
    String tokenFromHeader = headers.get(Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER));
    if (tokenFromHeader != null) {
      Token token = this.tokenService.getTokenByToken(tokenFromHeader);
      User user = this.userService.get(token.getUserId());
      BearerAuthenticationToken bearerAuthenticationToken = new BearerAuthenticationToken(
          token.getUserId(), token.getToken(), List.of(new GrantedAuthorityImpl(user.getRole())));
      bearerAuthenticationToken.setAuthenticated(true);
      return bearerAuthenticationToken;
    }
    return new AbstractAuthenticationToken(List.of()) {
      @Override
      public Object getCredentials() {
        return null;
      }

      @Override
      public Object getPrincipal() {
        return null;
      }
    };
  }

}
