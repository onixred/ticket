package my.pet.ticket.server.application.port.persistence;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;
import my.pet.ticket.server.ApplicationTest;
import my.pet.ticket.server.adapter.persistence.PersistenceAdapterException;
import my.pet.ticket.server.adapter.persistence.entity.ClientEntity;
import my.pet.ticket.server.adapter.persistence.entity.ClientEntity_;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;


class ClientPortTest extends ApplicationTest {

  @Autowired
  ClientPort clientPort;

  @Test
  void createTest() {
    ClientEntity clientEntity = ClientEntity.builder().firstName("Test4").lastName("Test4")
        .surName("Test4").fullName("Test4 Test4 Test4").email("test4@test.ru")
        .createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).deleted(false).build();
    ClientEntity client = this.clientPort.create(clientEntity);
    assertNotNull(client.getClientId());
  }

  @Test
  void getTestNotThrow() {
    Assertions.assertDoesNotThrow(() -> {
      this.clientPort.get(
              (Specification<ClientEntity>) (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                  root.get(ClientEntity_.CLIENT_ID), 1001))
          .orElseThrow(() -> new PersistenceAdapterException("Client not found"));
    });
  }

  @Test
  void getTestThrow() {
    Assertions.assertThrows(PersistenceAdapterException.class, () -> {
      this.clientPort.get(
              (Specification<ClientEntity>) (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                  root.get(ClientEntity_.CLIENT_ID), 999))
          .orElseThrow(() -> new PersistenceAdapterException("Client not found"));
    });
  }

  @Test
  void getAllTest() {
    List<ClientEntity> clientEntities = this.clientPort.getAll(
        (Specification<ClientEntity>) (root, query, criteriaBuilder) -> criteriaBuilder.conjunction());
    assertFalse(clientEntities.isEmpty());
  }

  @Test
  void updateNotThrow() {
    ClientEntity client = this.clientPort.get(
            (Specification<ClientEntity>) (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(ClientEntity_.CLIENT_ID), 1001))
        .orElseThrow(() -> new PersistenceAdapterException("Client not found"));
    Assertions.assertNotEquals("test4@test.ru", client.getEmail());
    client.setEmail("test4_new@test.ru");
    ClientEntity updatedClient = this.clientPort.update(client);
    Assertions.assertEquals("test4_new@test.ru", updatedClient.getEmail());
  }

  @Test
  void updateThrow() {
    ClientEntity client = this.clientPort.get(
            (Specification<ClientEntity>) (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(ClientEntity_.CLIENT_ID), 1001))
        .orElseThrow(() -> new PersistenceAdapterException("Client not found"));
    client.setClientId(999L);
    Assertions.assertThrows(PersistenceAdapterException.class, () -> {
      this.clientPort.update(client);
    });
  }

  @Test
  void deleteNotThrow() {
    Assertions.assertDoesNotThrow(() -> {
      ClientEntity client = this.clientPort.get(
              (Specification<ClientEntity>) (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                  root.get(ClientEntity_.CLIENT_ID), 1002))
          .orElseThrow(() -> new PersistenceAdapterException("Client not found"));
      this.clientPort.delete(client);
    });
  }

  @Test
  void deleteThrow() {
    Assertions.assertThrows(PersistenceAdapterException.class, () -> {
      this.clientPort.get(
              (Specification<ClientEntity>) (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                  root.get(ClientEntity_.CLIENT_ID), 1003))
          .orElseThrow(() -> new PersistenceAdapterException("Client not found"));
    });
  }

}