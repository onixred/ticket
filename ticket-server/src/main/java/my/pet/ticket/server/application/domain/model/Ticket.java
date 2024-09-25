package my.pet.ticket.server.application.domain.model;

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
public class Ticket {

  private Long ticketId;

  private Client client;

  private User author;

  private User manager;

  private TicketStatus ticketStatus;

  private String title;

  private String description;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Ticket ticket)) {
      return false;
    }
    return Objects.equals(ticketId, ticket.ticketId) && Objects.equals(client,
        ticket.client) && Objects.equals(author, ticket.author) && Objects.equals(
        manager, ticket.manager) && Objects.equals(ticketStatus, ticket.ticketStatus)
        && Objects.equals(title, ticket.title) && Objects.equals(description,
        ticket.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ticketId, client, author, manager, ticketStatus, title, description);
  }
}
