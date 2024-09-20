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
@Table(schema = "pet_project", name = "users")
@SequenceGenerator(
        name = "users_id_pk_seq",
        schema = "pet_project",
        sequenceName = "users_id_pk_seq",
        initialValue = 1001,
        allocationSize = 0
)
public class UserEntity
        extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_pk_seq")
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "sur_name")
    private String surName;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "suspended")
    private Boolean suspended;

    @Builder
    public UserEntity (
            Long userId,
            Long roleId,
            String firstName,
            String lastName,
            String surName,
            String fullName,
            String login,
            String password,
            Boolean active,
            Boolean suspended,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            Boolean deleted
    ) {
        this.userId = userId;
        this.roleId = roleId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.surName = surName;
        this.fullName = fullName;
        this.login = login;
        this.password = password;
        this.active = active;
        this.suspended = suspended;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
    }

    @Override
    public boolean equals (Object o) {
        if(this == o) return true;
        if(! (o instanceof UserEntity userDao)) return false;
        return Objects.equals(userId, userDao.userId) &&
               Objects.equals(roleId, userDao.roleId) &&
               Objects.equals(firstName, userDao.firstName) &&
               Objects.equals(lastName, userDao.lastName) &&
               Objects.equals(surName, userDao.surName) &&
               Objects.equals(login, userDao.login) &&
               Objects.equals(password, userDao.password) &&
               Objects.equals(active, userDao.active) &&
               Objects.equals(suspended, userDao.suspended);
    }

    @Override
    public int hashCode () {
        return Objects.hash(userId, roleId, firstName, lastName, surName, login, password, active, suspended);
    }

}
