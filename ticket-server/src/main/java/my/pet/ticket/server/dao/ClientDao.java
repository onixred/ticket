package my.pet.ticket.server.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(schema = "pet_project", name = "clients")
@EqualsAndHashCode(callSuper = true)
public class ClientDao extends AbstractDao {

    @Id
    @Column(name = "ticket_id")
    private Long ticketId;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "manager_id")
    private Long managerId;

}
