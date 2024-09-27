package my.pet.ticket.server.common.security;

import io.grpc.Metadata;
import io.grpc.Metadata.Key;
import io.grpc.ServerCall;
import java.util.List;
import javax.annotation.Nullable;
import net.devh.boot.grpc.server.security.authentication.GrpcAuthenticationReader;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class BearerAuthenticationReader implements GrpcAuthenticationReader {

  @Nullable
  @Override
  public Authentication readAuthentication(ServerCall<?, ?> call, Metadata headers)
      throws AuthenticationException {
    String tokenFromHeader = headers.get(Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER));
    if (tokenFromHeader != null) {
      return new AbstractAuthenticationToken(List.of()) {
        @Override
        public Object getCredentials() {
          return "anonymous";
        }

        @Override
        public Object getPrincipal() {
          return tokenFromHeader;
        }
      };
    }
    return null;
  }

}
