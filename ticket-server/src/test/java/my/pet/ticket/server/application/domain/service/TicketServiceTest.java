package my.pet.ticket.server.application.domain.service;

import java.util.List;
import my.pet.ticket.application.domain.model.Ticket;
import my.pet.ticket.server.ApplicationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class TicketServiceTest extends ApplicationTest {

  @Autowired
  TicketService ticketService;

  @Test
  void getTicketTest() {
    Assertions.assertDoesNotThrow(() -> {
      this.ticketService.get(10001L);
    });
  }

  @Test
  void getTicketByClientIdTest() {
    Assertions.assertDoesNotThrow(() -> {
      this.ticketService.getByClientId(1005L);
    });
  }

  @Test
  void getAllTicketsTest() {
    List<Ticket> tickets = this.ticketService.getAll();
    Assertions.assertFalse(tickets.isEmpty());
  }

  @Test
  void getAllTicketsPageableTest() {
    List<Ticket> tickets1 = this.ticketService.getAll(0, 1);
    List<Ticket> tickets2 = this.ticketService.getAll(1, 1);
    Assertions.assertNotEquals(tickets1.get(0), tickets2.get(0));
  }

  @Test
  void deleteTicketTest() {
    Assertions.assertDoesNotThrow(() -> {
      this.ticketService.delete(10004L);
    });
  }

  @Test
  void deleteTicketByClientIdTest() {
    Assertions.assertDoesNotThrow(() -> {
      this.ticketService.deleteByClientId(1002L);
    });
  }

}