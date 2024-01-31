package my.pet.ticket.dataLayer.model.dto;

import lombok.Data;
import my.pet.ticket.dataLayer.model.TicketStatus;

@Data
public class TicketDTO {
    private Long id;
    private String taskName;
    private String description;
    private TicketStatus status;
    private UserDTO author;
    private UserDTO executor;
}
