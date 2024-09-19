package my.pet.ticket.server.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "pet_project", name = "clients")
@SequenceGenerator(
        name = "client_id_pk_seq",
        schema = "pet_project",
        sequenceName = "client_id_pk_seq",
        initialValue = 1001,
        allocationSize = 0
)
public class ClientEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_id_pk_seq")
    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "sur_name")
    private String surName;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Builder
    public ClientEntity(Long clientId, String firstName, String lastName, String surName, String fullName, String email, LocalDateTime createdAt, LocalDateTime updatedAt, Boolean deleted) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.surName = surName;
        this.fullName = fullName;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientEntity that)) return false;
        return Objects.equals(clientId, that.clientId) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(surName, that.surName) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, firstName, lastName, surName, email);
    }

}
