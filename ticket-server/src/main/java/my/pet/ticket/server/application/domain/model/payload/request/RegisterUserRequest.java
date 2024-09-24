package my.pet.ticket.server.application.domain.model.payload.request;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {

  private String fullName;

  private String login;

  private String password;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof RegisterUserRequest request)) {
      return false;
    }
    return Objects.equals(fullName, request.fullName) && Objects.equals(login,
        request.login) && Objects.equals(password, request.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fullName, login, password);
  }
}
