package my.pet.ticket.server.adapter.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "pet_project", name = "tickets")
@SequenceGenerator(
        name = "tickets_id_pk_seq",
        schema = "pet_project",
        sequenceName = "tickets_id_pk_seq",
        initialValue = 1001,
        allocationSize = 0
)
public class TicketEntity
        extends AbstractEntity {

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

    @Builder
    public TicketEntity(
            Long ticketId,
            Long clientId,
            Long authorId,
            Long managerId,
            Long ticketStatusId,
            String title,
            String description,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            Boolean deleted
    ) {
        this.id = new TicketIdEntity();
        this.id.setTicketId(ticketId);
        this.id.setClientId(clientId);
        this.authorId = authorId;
        this.managerId = managerId;
        this.ticketStatusId = ticketStatusId;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TicketEntity ticketDao)) {
            return false;
        }
        return Objects.equals(id, ticketDao.id) &&
                Objects.equals(authorId, ticketDao.authorId) &&
                Objects.equals(managerId, ticketDao.managerId) &&
                Objects.equals(ticketStatusId, ticketDao.ticketStatusId) &&
                Objects.equals(title, ticketDao.title) &&
                Objects.equals(description, ticketDao.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorId, managerId, ticketStatusId, title, description);
    }

}
