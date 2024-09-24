package my.pet.ticket.server.application.domain.model;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

  private Long userId;

  private Role role;

  private String fullName;

  private String login;

  private Boolean active;

  private Boolean suspended;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof User user)) {
      return false;
    }
    return Objects.equals(userId, user.userId) && Objects.equals(role, user.role)
        && Objects.equals(fullName, user.fullName) && Objects.equals(login,
        user.login) && Objects.equals(active, user.active) && Objects.equals(
        suspended, user.suspended);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, role, fullName, login, active, suspended);
  }
}
