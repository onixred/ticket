package my.pet.ticket.server.application.domain.model;

import lombok.Data;

@Data
public class User {

    private Long userId;

    private Role role;

    private String fullName;

    private String login;

    private Boolean active;

    private Boolean suspended;

}
