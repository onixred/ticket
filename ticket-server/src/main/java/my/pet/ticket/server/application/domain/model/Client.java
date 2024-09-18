package my.pet.ticket.server.application.domain.model;

import lombok.Data;

@Data
public class Client {

    private Long clientId;

    private String fullName;

    private String email;

    private String phoneNumber;

}
