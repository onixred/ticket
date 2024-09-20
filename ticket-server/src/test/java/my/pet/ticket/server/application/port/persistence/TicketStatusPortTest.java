package my.pet.ticket.server.application.port.persistence;

import my.pet.ticket.server.adapter.persistence.PersistenceAdapterException;
import my.pet.ticket.server.adapter.persistence.entity.TicketStatusEntity;
import my.pet.ticket.server.adapter.persistence.entity.TicketStatusEntity_;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class TicketStatusPortTest {

    @Autowired
    TicketStatusPort ticketStatusPort;

    @Test
    void createTest() {
        TicketStatusEntity ticketStatusEntity = TicketStatusEntity.builder()
                .name("Ticket in progress")
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
            this.ticketStatusPort.get(((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(TicketStatusEntity_.TICKET_STATUS_ID), 1)))
                    .orElseThrow(() -> new PersistenceAdapterException("Ticket status not found"));
        });
    }

    @Test
    void getTestThrows() {
        Assertions.assertThrows(PersistenceAdapterException.class, () -> {
            this.ticketStatusPort.get(((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(TicketStatusEntity_.TICKET_STATUS_ID), 0)))
                    .orElseThrow(() -> new PersistenceAdapterException("Ticket status not found"));
        });
    }

    @Test
    void getAllTest() {
        List<TicketStatusEntity> ticketStatusEntities = this.ticketStatusPort.getAll(((root, query, criteriaBuilder) -> criteriaBuilder.conjunction()));
        assertFalse(ticketStatusEntities.isEmpty());
    }

    @Test
    void updateTestNotThrows() {
        TicketStatusEntity ticketStatusEntity = this.ticketStatusPort.get(((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(TicketStatusEntity_.TICKET_STATUS_ID), 1)))
                .orElseThrow(() -> new PersistenceAdapterException("Ticket status not found"));
        ticketStatusEntity.setName("New name ticket created");
        TicketStatusEntity updateTicketStatusEntity = this.ticketStatusPort.update(ticketStatusEntity);
        Assertions.assertEquals("New name ticket created", updateTicketStatusEntity.getName());
    }

    @Test
    void updateTestThrows() {
        TicketStatusEntity ticketStatusEntity = this.ticketStatusPort.get(((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(TicketStatusEntity_.TICKET_STATUS_ID), 1)))
                .orElseThrow(() -> new PersistenceAdapterException("Ticket status not found"));
        ticketStatusEntity.setTicketStatusId(0L);
        Assertions.assertThrows(PersistenceAdapterException.class, () -> {
            this.ticketStatusPort.update(ticketStatusEntity);
        });
    }

    @Test
    void deleteTestNotThrows() {
        TicketStatusEntity ticketStatusEntity = this.ticketStatusPort.get(((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(TicketStatusEntity_.TICKET_STATUS_ID), 1)))
                .orElseThrow(() -> new PersistenceAdapterException("Ticket status not found"));
        this.ticketStatusPort.delete(ticketStatusEntity);
        TicketStatusEntity deletedTicketStatusEntity = this.ticketStatusPort.get(((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(TicketStatusEntity_.TICKET_STATUS_ID), 1)))
                .orElseThrow(() -> new PersistenceAdapterException("Ticket status not found"));
        Assertions.assertTrue(deletedTicketStatusEntity.getDeleted());
    }

    @Test
    void deleteTestThrows() {
        TicketStatusEntity ticketStatusEntity = this.ticketStatusPort.get(((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(TicketStatusEntity_.TICKET_STATUS_ID), 1)))
                .orElseThrow(() -> new PersistenceAdapterException("Ticket status not found"));
        ticketStatusEntity.setTicketStatusId(0L);
        Assertions.assertThrows(PersistenceAdapterException.class, () -> {
            this.ticketStatusPort.delete(ticketStatusEntity);
        });
    }

}