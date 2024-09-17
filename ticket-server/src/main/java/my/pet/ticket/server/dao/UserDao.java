package my.pet.ticket.server.dao;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(schema = "pet_project", name = "users")
@EqualsAndHashCode(callSuper = true)
public class UserDao extends AbstractDao {

    @Id
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

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "suspended")
    private Boolean suspended;

}
