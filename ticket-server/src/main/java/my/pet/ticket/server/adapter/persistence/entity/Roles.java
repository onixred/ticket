package my.pet.ticket.server.adapter.persistence.entity;

import lombok.Getter;

@Getter
public enum Roles {

  GUEST(1L),

  MANAGER(2L),

  ADMIN(3L);

  private final Long roleId;

  Roles(Long roleId) {
    this.roleId = roleId;
  }

}
