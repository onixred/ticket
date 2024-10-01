package my.pet.ticket.server.application.port.persistence;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;
import my.pet.ticket.server.ApplicationTest;
import my.pet.ticket.server.adapter.persistence.PersistenceAdapterException;
import my.pet.ticket.server.adapter.persistence.entity.TicketStatusEntity;
import my.pet.ticket.server.adapter.persistence.entity.TicketStatusEntity_;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;


class TicketStatusPortTest extends ApplicationTest {

  @Autowired
  TicketStatusPort ticketStatusPort;

  @Test
  void createTest() {
    TicketStatusEntity ticketStatusEntity = TicketStatusEntity.builder()
        .name("Test status 4")
        .active(true)
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .deleted(false)
        .build();
    TicketStatusEntity ticketStatus = this.ticketStatusPort.create(ticketStatusEntity);
    Assertions.assertNotNull(ticketStatus.getTicketStatusId());
  }

  @Test
  void getTestNotThrows() {
    Assertions.assertDoesNotThrow(() -> {
      this.ticketStatusPort.get(((root, query, criteriaBuilder) -> criteriaBuilder.equal(
              root.get(TicketStatusEntity_.TICKET_STATUS_ID), 5)))
          .orElseThrow(() -> new PersistenceAdapterException("Ticket status not found"));
    });
  }

  @Test
  void getTestThrows() {
    Assertions.assertThrows(PersistenceAdapterException.class, () -> {
      this.ticketStatusPort.get(((root, query, criteriaBuilder) -> criteriaBuilder.equal(
              root.get(TicketStatusEntity_.TICKET_STATUS_ID), 0)))
          .orElseThrow(() -> new PersistenceAdapterException("Ticket status not found"));
    });
  }

  @Test
  void getAllTest() {
    Page<T> ticketStatusEntities = this.ticketStatusPort.getAll(
        ((root, query, criteriaBuilder) -> criteriaBuilder.conjunction()));
    assertFalse(ticketStatusEntities.isEmpty());
  }

  @Test
  void updateTestNotThrows() {
    TicketStatusEntity ticketStatusEntity = this.ticketStatusPort.get(
            ((root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(TicketStatusEntity_.TICKET_STATUS_ID), 5)))
        .orElseThrow(() -> new PersistenceAdapterException("Ticket status not found"));
    ticketStatusEntity.setName("New name ticket created");
    TicketStatusEntity updateTicketStatusEntity = this.ticketStatusPort.update(ticketStatusEntity);
    Assertions.assertEquals("New name ticket created", updateTicketStatusEntity.getName());
  }

  @Test
  void updateTestThrows() {
    TicketStatusEntity ticketStatusEntity = this.ticketStatusPort.get(
            ((root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(TicketStatusEntity_.TICKET_STATUS_ID), 5)))
        .orElseThrow(() -> new PersistenceAdapterException("Ticket status not found"));
    ticketStatusEntity.setTicketStatusId(0L);
    Assertions.assertThrows(PersistenceAdapterException.class, () -> {
      this.ticketStatusPort.update(ticketStatusEntity);
    });
  }

  @Test
  void deleteTestNotThrows() {
    Assertions.assertDoesNotThrow(() -> {
      TicketStatusEntity ticketStatusEntity = this.ticketStatusPort.get(
              ((root, query, criteriaBuilder) -> criteriaBuilder.equal(
                  root.get(TicketStatusEntity_.TICKET_STATUS_ID), 6)))
          .orElseThrow(() -> new PersistenceAdapterException("Ticket status not found"));
      this.ticketStatusPort.delete(ticketStatusEntity);
    });
  }

  @Test
  void deleteTestThrows() {
    Assertions.assertThrows(PersistenceAdapterException.class, () -> {
      this.ticketStatusPort.get(((root, query, criteriaBuilder) -> criteriaBuilder.equal(
              root.get(TicketStatusEntity_.TICKET_STATUS_ID), 7)))
          .orElseThrow(() -> new PersistenceAdapterException("Ticket status not found"));
    });
  }

}