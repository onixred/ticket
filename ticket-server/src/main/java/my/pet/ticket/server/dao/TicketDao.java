package my.pet.ticket.server.dao;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(schema = "pet_project", name = "tickets")
@EqualsAndHashCode(callSuper = true)
public class TicketDao extends AbstractDao {

    @EmbeddedId
    private TicketIdDao id;

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

}
