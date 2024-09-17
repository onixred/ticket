package my.pet.ticket.server.dao;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(schema = "pet_project", name = "roles")
@EqualsAndHashCode(callSuper = true)
public class RoleDao extends AbstractDao {

    @Id
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private String active;

}
