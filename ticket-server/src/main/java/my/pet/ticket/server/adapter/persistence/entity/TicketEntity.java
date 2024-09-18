package my.pet.ticket.server.adapter.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(schema = "pet_project", name = "tickets")
public class TicketEntity extends AbstractEntity {

    @EmbeddedId
    private TicketIdEntity id;

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "manager_id")
    private Long managerId;

    @Column(name = "ticket_status_id")
    private Long ticketStatusId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TicketEntity ticketDao)) return false;
        return Objects.equals(id, ticketDao.id) && Objects.equals(authorId, ticketDao.authorId) && Objects.equals(managerId, ticketDao.managerId) && Objects.equals(ticketStatusId, ticketDao.ticketStatusId) && Objects.equals(title, ticketDao.title) && Objects.equals(description, ticketDao.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorId, managerId, ticketStatusId, title, description);
    }
}
