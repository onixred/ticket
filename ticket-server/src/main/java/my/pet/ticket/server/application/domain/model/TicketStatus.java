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
public class TicketStatus {

  private Long ticketStatusId;

  private String name;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TicketStatus that)) {
      return false;
    }
    return Objects.equals(ticketStatusId, that.ticketStatusId) && Objects.equals(
        name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ticketStatusId, name);
  }
}
