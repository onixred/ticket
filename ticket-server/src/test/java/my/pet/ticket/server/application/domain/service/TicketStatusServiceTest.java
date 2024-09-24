package my.pet.ticket.server.application.domain.service;

import java.util.List;
import my.pet.ticket.server.ApplicationTest;
import my.pet.ticket.server.application.domain.model.TicketStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class TicketStatusServiceTest extends ApplicationTest {

  @Autowired
  TicketStatusService ticketStatusService;

  @Test
  void getTicketStatusTest() {
    Assertions.assertDoesNotThrow(() -> {
      this.ticketStatusService.get(1L);
    });
  }

  @Test
  void getAllTicketStatusesTest() {
    List<TicketStatus> ticketStatuses = this.ticketStatusService.getAll();
    Assertions.assertFalse(ticketStatuses.isEmpty());
  }

  @Test
  void getAllTicketStatusesPageableTest() {
    List<TicketStatus> ticketStatuses1 = this.ticketStatusService.getAll(0, 1);
    List<TicketStatus> ticketStatuses2 = this.ticketStatusService.getAll(1, 1);
    Assertions.assertNotEquals(ticketStatuses1.get(0), ticketStatuses2.get(0));
  }

  @Test
  void deleteTicketStatusTest() {
    Assertions.assertDoesNotThrow(() -> {
      this.ticketStatusService.delete(8L);
    });
  }

}