package my.pet.ticket.server.application.domain.model.payload.request;

import lombok.Data;

@Data
public class LoginRequest {

  private String login;

  private String password;

}
