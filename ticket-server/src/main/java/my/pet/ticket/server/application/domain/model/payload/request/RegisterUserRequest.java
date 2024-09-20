package my.pet.ticket.server.application.domain.model.payload.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserRequest {

  @JsonAlias("fullName")
  private String fullName;

  @JsonAlias("login")
  private String login;

  @JsonAlias("password")
  private String password;

}
