package my.pet.ticket.server.adapter.persistence.entity;

import lombok.Getter;
import my.pet.ticket.server.application.domain.service.DomainServiceException;

@Getter
public enum Roles {

  GUEST(1L),

  MANAGER(2L),

  ADMIN(3L);

  private final Long roleId;

  Roles(Long roleId) {
    this.roleId = roleId;
  }

  public static Roles getRole(Long id) {
    for (Roles role : Roles.values()) {
      if (role.roleId.equals(id)) {
        return role;
      }
    }
    throw new DomainServiceException("Role not match with provided id");
  }

}
