package my.pet.ticket.server.common.security;

import java.util.Collection;
import java.util.List;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

@Setter
public class BearerAuthenticationToken extends AbstractAuthenticationToken {

  private Long userId;

  private String token;

  public BearerAuthenticationToken() {
    super(List.of());
  }

  public BearerAuthenticationToken(Long userId, String token,
      Collection<? extends GrantedAuthority> authorities) {
    super(authorities);
    this.userId = userId;
    this.token = token;
  }

  @Override
  public Object getCredentials() {
    return this.token;
  }

  @Override
  public Object getPrincipal() {
    return this.userId;
  }

}
