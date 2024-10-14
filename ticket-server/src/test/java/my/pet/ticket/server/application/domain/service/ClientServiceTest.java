package my.pet.ticket.server.application.domain.service;

import java.util.List;
import my.pet.ticket.application.domain.model.Client;
import my.pet.ticket.server.ApplicationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ClientServiceTest extends ApplicationTest {

  @Autowired
  ClientService clientService;

  @Test
  void getClientTest() {
    Assertions.assertDoesNotThrow(() -> {
      this.clientService.get(1001L);
    });
  }

  @Test
  void getAllClientsTest() {
    List<Client> clients = this.clientService.getAll();
    Assertions.assertFalse(clients.isEmpty());
  }

  @Test
  void getAllClientsPageableTest() {
    List<Client> clients1 = this.clientService.getAll(0, 1);
    List<Client> clients2 = this.clientService.getAll(1, 1);
    Assertions.assertNotEquals(clients1.get(0), clients2.get(0));
  }

  @Test
  void deleteClientTest() {
    Assertions.assertDoesNotThrow(() -> {
      this.clientService.delete(1004L);
    });
  }

}