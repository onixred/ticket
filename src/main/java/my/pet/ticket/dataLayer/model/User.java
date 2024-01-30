package my.pet.ticket.dataLayer.model;

import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String login;
    private String password;
    private Date createdAt;
    private Long createdBy;
    private Date updatedAt;
    private boolean active;
}
