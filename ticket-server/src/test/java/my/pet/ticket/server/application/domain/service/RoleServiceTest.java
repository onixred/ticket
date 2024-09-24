package my.pet.ticket.server.application.domain.service;

import java.util.List;
import my.pet.ticket.server.ApplicationTest;
import my.pet.ticket.server.application.domain.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class RoleServiceTest extends ApplicationTest {

  @Autowired
  RoleService roleService;

  @Test
  void getRoleTest() {
    Assertions.assertDoesNotThrow(() -> {
      this.roleService.get(1L);
    });
  }

  @Test
  void getAllRoleTest() {
    List<Role> roles = this.roleService.getAll();
    Assertions.assertFalse(roles.isEmpty());
  }

  @Test
  void getAllPageableRoleTest() {
    List<Role> roles1 = this.roleService.getAll(0, 1);
    List<Role> roles2 = this.roleService.getAll(1, 1);
    Assertions.assertNotEquals(roles1.get(0), roles2.get(0));
  }

  @Test
  void deleteRoleTest() {
    Assertions.assertDoesNotThrow(() -> {
      this.roleService.delete(7L);
    });
  }

}