package my.pet.ticket.client.application.port.api;

import java.util.List;
import my.pet.ticket.application.domain.model.Ticket;

public interface TicketGrpcPort {

  List<Ticket> getAllTickets(String token);

}
