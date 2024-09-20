package my.pet.ticket.server.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "pet_project", name = "ticket_statuses")
@SequenceGenerator(
        name = "ticket_statuses_id_pk_seq",
        schema = "pet_project",
        sequenceName = "ticket_statuses_id_pk_seq",
        initialValue = 1001,
        allocationSize = 0
)
public class TicketStatusEntity
        extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_statuses_id_pk_seq")
    @Column(name = "ticket_status_id")
    private Long ticketStatusId;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private Boolean active;

    @Builder
    public TicketStatusEntity (
            Long ticketStatusId,
            String name,
            Boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            Boolean deleted
    ) {
        this.ticketStatusId = ticketStatusId;
        this.name = name;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
    }

    @Override
    public boolean equals (Object o) {
        if(this == o) return true;
        if(! (o instanceof TicketStatusEntity that)) return false;
        return Objects.equals(ticketStatusId, that.ticketStatusId) &&
               Objects.equals(name, that.name) &&
               Objects.equals(active, that.active);
    }

    @Override
    public int hashCode () {
        return Objects.hash(ticketStatusId, name, active);
    }

}
