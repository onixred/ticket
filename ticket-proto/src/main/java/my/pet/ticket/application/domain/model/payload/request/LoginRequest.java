package my.pet.ticket.application.domain.model.payload.request;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

  private String login;

  private String password;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof LoginRequest that)) {
      return false;
    }
    return Objects.equals(login, that.login) && Objects.equals(password,
        that.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(login, password);
  }
}
