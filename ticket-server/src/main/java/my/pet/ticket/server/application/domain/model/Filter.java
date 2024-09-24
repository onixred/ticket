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
public class Filter {

  private Long clientId;

  private Long userId;

  private Long ticketId;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Filter filter)) {
      return false;
    }
    return Objects.equals(clientId, filter.clientId) && Objects.equals(userId,
        filter.userId) && Objects.equals(ticketId, filter.ticketId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clientId, userId, ticketId);
  }
}
