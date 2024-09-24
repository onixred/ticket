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
public class Client {

  private Long clientId;

  private String fullName;

  private String email;

  private String phoneNumber;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Client client)) {
      return false;
    }
    return Objects.equals(clientId, client.clientId) && Objects.equals(fullName,
        client.fullName) && Objects.equals(email, client.email) && Objects.equals(
        phoneNumber, client.phoneNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clientId, fullName, email, phoneNumber);
  }
}
