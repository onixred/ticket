package my.pet.ticket.application.domain.model;

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
