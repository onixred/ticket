package my.pet.ticket.server.application.port.persistence;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;
import java.util.List;
import my.pet.ticket.server.adapter.persistence.PersistenceAdapterException;
import my.pet.ticket.server.adapter.persistence.entity.RoleEntity;
import my.pet.ticket.server.adapter.persistence.entity.RoleEntity_;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
class RolesPortTest {

  @Autowired
  RolePort rolePort;

  @Test
  void createTest() {
    RoleEntity roleEntity = RoleEntity.builder().name("Test role 4").active(true)
        .createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).deleted(false).build();
    RoleEntity role = this.rolePort.create(roleEntity);
    Assertions.assertNotNull(role.getRoleId());
  }

  @Test
  void getTestNotThrow() {
    Assertions.assertDoesNotThrow(() -> {
      this.rolePort.get(
          ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(RoleEntity_.ROLE_ID),
              1))).orElseThrow(() -> new PersistenceAdapterException("Role not found"));
    });
  }

  @Test
  void getTestThrow() {
    Assertions.assertThrows(PersistenceAdapterException.class, () -> {
      this.rolePort.get(
          ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(RoleEntity_.ROLE_ID),
              0))).orElseThrow(() -> new PersistenceAdapterException("Role not found"));
    });
  }

  @Test
  void getAllTest() {
    List<RoleEntity> roleEntities = this.rolePort.getAll(
        (root, query, criteriaBuilder) -> criteriaBuilder.conjunction());
    assertFalse(roleEntities.isEmpty());
  }

  @Test
  void updateTestNotThrow() {
    RoleEntity role = this.rolePort.get(
            ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(RoleEntity_.ROLE_ID), 4)))
        .orElseThrow(() -> new PersistenceAdapterException("Role not found"));
    role.setName("New Name");
    RoleEntity updateRole = this.rolePort.update(role);
    Assertions.assertEquals("New Name", updateRole.getName());
  }

  @Test
  void updateTestThrow() {
    RoleEntity role = this.rolePort.get(
            ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(RoleEntity_.ROLE_ID), 4)))
        .orElseThrow(() -> new PersistenceAdapterException("Role not found"));
    role.setRoleId(0L);
    Assertions.assertThrows(PersistenceAdapterException.class, () -> {
      this.rolePort.update(role);
    });
  }

  @Test
  void deleteNotThrowTest() {
    Assertions.assertDoesNotThrow(() -> {
      RoleEntity role = this.rolePort.get(
              ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(RoleEntity_.ROLE_ID),
                  5)))
          .orElseThrow(() -> new PersistenceAdapterException("Role not found"));
      this.rolePort.delete(role);
    });
  }

  @Test
  void deleteThrowTest() {
    Assertions.assertThrows(PersistenceAdapterException.class, () -> {
      this.rolePort.get(
          ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(RoleEntity_.ROLE_ID),
              6))).orElseThrow(() -> new PersistenceAdapterException("Role not found"));
    });
  }

}