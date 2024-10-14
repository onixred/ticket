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
public class RegisterUserRequest {

  private String fullName;

  private String login;

  private String password;

  private String rePassword;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof RegisterUserRequest that)) {
      return false;
    }
    return Objects.equals(fullName, that.fullName) && Objects.equals(login,
        that.login) && Objects.equals(password, that.password) && Objects.equals(
        rePassword, that.rePassword);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fullName, login, password, rePassword);
  }

}
