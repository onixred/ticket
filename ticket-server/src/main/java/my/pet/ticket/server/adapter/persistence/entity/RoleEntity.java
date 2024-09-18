package my.pet.ticket.server.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(schema = "pet_project", name = "roles")
public class RoleEntity extends AbstractEntity {

    @Id
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private String active;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleEntity roleDao)) return false;
        return Objects.equals(roleId, roleDao.roleId) && Objects.equals(name, roleDao.name) && Objects.equals(active, roleDao.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, name, active);
    }
}
