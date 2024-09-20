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
@Table(schema = "pet_project", name = "roles")
@SequenceGenerator(
        name = "roles_id_pk_seq",
        schema = "pet_project",
        sequenceName = "roles_id_pk_seq",
        initialValue = 1001,
        allocationSize = 0
)
public class RoleEntity
        extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_id_pk_seq")
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private Boolean active;

    @Builder
    public RoleEntity (
            Long roleId,
            String name,
            Boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            Boolean deleted
    ) {
        this.roleId = roleId;
        this.name = name;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
    }

    @Override
    public boolean equals (Object o) {
        if(this == o) return true;
        if(! (o instanceof RoleEntity roleDao)) return false;
        return Objects.equals(roleId, roleDao.roleId) &&
               Objects.equals(name, roleDao.name) &&
               Objects.equals(active, roleDao.active);
    }

    @Override
    public int hashCode () {
        return Objects.hash(roleId, name, active);
    }

}
