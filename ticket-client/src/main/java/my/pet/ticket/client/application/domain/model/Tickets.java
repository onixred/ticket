package my.pet.ticket.client.application.domain.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import my.pet.ticket.application.domain.model.Ticket;

@Data
@AllArgsConstructor
public class Tickets {

  private List<Ticket> tickets;

  private Long pages;

}
