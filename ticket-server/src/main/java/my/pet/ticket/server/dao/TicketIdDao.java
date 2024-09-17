package my.pet.ticket.server.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class TicketIdDao {

    @Column(name = "ticket_id")
    private Long ticketId;

    @Column(name = "client_id")
    private Long clientId;

}
