package my.pet.ticket.server.application.domain.model;

import lombok.Data;

@Data
public class Ticket {

    private Long ticketId;

    private Client clientId;

    private User authorId;

    private User managerId;

    private String ticketStatus;

    private String title;

    private String description;

}
