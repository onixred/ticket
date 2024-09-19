package my.pet.ticket.server.application.domain.model;

import lombok.Data;

@Data
public class Filter {

    private Long clientId;

    private Long userId;

    private Long ticketId;

}
