package my.pet.ticket.server.adapter.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(schema = "pet_project", name = "ticket_statuses")
public class TicketStatusEntity extends AbstractEntity {

    @Id
    @Column(name = "ticket_status_id")
    private Long ticketStatusId;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private Boolean active;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TicketStatusEntity that)) return false;
        return Objects.equals(ticketStatusId, that.ticketStatusId) && Objects.equals(name, that.name) && Objects.equals(active, that.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketStatusId, name, active);
    }
}
