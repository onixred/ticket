package my.pet.ticket.dataLayer.model.dto;

import lombok.Data;

@Data
public class TicketDTO {
    private Long id;
    private String taskName;
    private String description;
    private String status;
    private UserDTO author;
    private UserDTO executor;
}
