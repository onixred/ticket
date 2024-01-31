package my.pet.ticket.dataLayer.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=2, message = "Не меньше 5 знаков")
    private String fullName;

    @Column(unique = true)
    @NotNull
    private String email;

    @Column(unique = true)
    @NotNull
    private String login;

    @Size(min=2, message = "Не меньше 5 знаков")
    @NotNull
    private String password;

    @NotNull
    private Date createdAt;
    @NotNull
    private Long createdBy;
    @NotNull
    private Date updatedAt;
    private boolean active;
}
