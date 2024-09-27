package my.pet.ticket.client.application.domain.model;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role {

  private Long roleId;

  private String name;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Role role)) {
      return false;
    }
    return Objects.equals(roleId, role.roleId) && Objects.equals(name, role.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(roleId, name);
  }
}
