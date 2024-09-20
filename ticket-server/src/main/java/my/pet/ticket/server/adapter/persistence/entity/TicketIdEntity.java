package my.pet.ticket.server.adapter.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.Objects;
import lombok.Data;

@Data
@Embeddable
public class TicketIdEntity {

    @Column(name = "ticket_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tickets_id_pk_seq")
    private Long ticketId;

    @Column(name = "client_id")
    private Long clientId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TicketIdEntity that)) {
            return false;
        }
        return Objects.equals(ticketId, that.ticketId) && Objects.equals(clientId, that.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, clientId);
    }

}
