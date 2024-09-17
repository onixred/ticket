package my.pet.ticket.server.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(schema = "pet_project", name = "ticket_statuses")
@EqualsAndHashCode(callSuper = true)
public class TicketStatusDao extends AbstractDao {

    @Id
    @Column(name = "ticket_status_id")
    private Long ticketStatusId;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private Boolean active;

}
