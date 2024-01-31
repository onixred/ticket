package my.pet.ticket.dataLayer.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=2, message = "Не меньше 5 знаков")
    @NotNull
    private String taskName;
    private String description;

    @ManyToOne
    @JoinColumn(name = "status_id")
    @NotNull
    private TicketStatus status;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @NotNull
    private User author;

    @ManyToOne
    @JoinColumn(name = "executor_id")
    @NotNull
    private User executor;
}
