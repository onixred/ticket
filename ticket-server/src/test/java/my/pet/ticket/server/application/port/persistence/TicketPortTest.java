package my.pet.ticket.server.application.port.persistence;

import my.pet.ticket.server.adapter.persistence.PersistenceAdapterException;
import my.pet.ticket.server.adapter.persistence.entity.TicketEntity;
import my.pet.ticket.server.adapter.persistence.entity.TicketEntity_;
import my.pet.ticket.server.adapter.persistence.entity.TicketIdEntity_;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TicketPortTest {

    @Autowired
    TicketPort ticketPort;

    @Test
    void createTest () {
        TicketEntity ticketEntity = TicketEntity.builder()
                                                .clientId(1001L)
                                                .authorId(1001L)
                                                .managerId(1002L)
                                                .ticketStatusId(1L)
                                                .title("Zakaz")
                                                .description("Opisanie zakaza")
                                                .createdAt(LocalDateTime.now())
                                                .updatedAt(LocalDateTime.now())
                                                .deleted(false)
                                                .build();
        TicketEntity ticket = this.ticketPort.create(ticketEntity);
        Assertions.assertNotNull(ticket.getId().getTicketId());
    }

    @Test
    void getTestNotThrows () {
        Assertions.assertDoesNotThrow(() -> {
            this.ticketPort.get((
                    (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                            root.get(TicketEntity_.ID)
                                .get(TicketIdEntity_.TICKET_ID),
                            10001
                    )
            )).orElseThrow(() -> new PersistenceAdapterException("Ticket not found"));
        });
    }

    @Test
    void getTestThrows () {
        Assertions.assertThrows(PersistenceAdapterException.class, () -> {
            this.ticketPort.get((
                    (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                            root.get(TicketEntity_.ID)
                                .get(TicketIdEntity_.TICKET_ID),
                            9999
                    )
            )).orElseThrow(() -> new PersistenceAdapterException("Ticket not found"));
        });
    }

    @Test
    void getAllTest () {
        List<TicketEntity> ticketEntities = this.ticketPort.getAll((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());
        assertFalse(ticketEntities.isEmpty());
    }

    @Test
    void updateTestNotThrows () {
        TicketEntity ticketEntity = this.ticketPort.get((
                (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(TicketEntity_.ID)
                                                                            .get(TicketIdEntity_.TICKET_ID), 10001)
        )).orElseThrow(() -> new PersistenceAdapterException("Ticket not found"));
        ticketEntity.setTitle("Noviy zagolovok");
        TicketEntity updatedTicketEntity = this.ticketPort.update(ticketEntity);
        Assertions.assertEquals("Noviy zagolovok", updatedTicketEntity.getTitle());
    }

    @Test
    void updateTestThrows () {
        TicketEntity ticketEntity = this.ticketPort.get((
                (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(TicketEntity_.ID)
                                                                            .get(TicketIdEntity_.TICKET_ID), 10001)
        )).orElseThrow(() -> new PersistenceAdapterException("Ticket not found"));
        ticketEntity.getId().setTicketId(9999L);
        Assertions.assertThrows(PersistenceAdapterException.class, () -> {
            this.ticketPort.update(ticketEntity);
        });
    }

    @Test
    void deletedNotThrows () {
        TicketEntity ticketEntity = this.ticketPort.get((
                (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(TicketEntity_.ID)
                                                                            .get(TicketIdEntity_.TICKET_ID), 10001)
        )).orElseThrow(() -> new PersistenceAdapterException("Ticket not found"));
        this.ticketPort.delete(ticketEntity);
        TicketEntity deletedTicketEntity = this.ticketPort.get((
                (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                        root.get(TicketEntity_.ID).get(TicketIdEntity_.TICKET_ID),
                        10001
                )
        )).orElseThrow(() -> new PersistenceAdapterException("Ticket not found"));
        assertTrue(deletedTicketEntity.getDeleted());
    }

    @Test
    void deletedThrows () {
        TicketEntity ticketEntity = this.ticketPort.get((
                (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(TicketEntity_.ID)
                                                                            .get(TicketIdEntity_.TICKET_ID), 10001)
        )).orElseThrow(() -> new PersistenceAdapterException("Ticket not found"));
        ticketEntity.getId().setTicketId(9999L);
        Assertions.assertThrows(PersistenceAdapterException.class, () -> {
            this.ticketPort.delete(ticketEntity);
        });
    }

}