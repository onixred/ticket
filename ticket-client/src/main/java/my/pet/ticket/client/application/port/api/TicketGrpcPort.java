package my.pet.ticket.client.application.port.api;

import java.util.List;
import my.pet.ticket.application.domain.model.Ticket;
import my.pet.ticket.application.domain.model.Tickets;

public interface TicketGrpcPort {

  List<Ticket> getAllTickets(String token);

  Tickets getAllTickets(String token, Integer page, Integer pageSize);

}
