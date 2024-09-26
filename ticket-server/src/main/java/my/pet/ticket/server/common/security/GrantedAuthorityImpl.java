package my.pet.ticket.server.common.security;

import my.pet.ticket.server.adapter.persistence.entity.Roles;
import my.pet.ticket.server.application.domain.model.Role;
import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityImpl implements GrantedAuthority {

  private final Roles role;

  public GrantedAuthorityImpl(Role role) {
    this.role = Roles.getRole(role.getRoleId());
  }

  @Override
  public String getAuthority() {
    return role.toString();
  }


}
