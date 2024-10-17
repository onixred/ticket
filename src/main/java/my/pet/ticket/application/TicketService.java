package my.pet.ticket.application;

import java.util.List;
import java.util.Optional;

import my.pet.ticket.application.model.Ticket;

public interface TicketService {
    
    Ticket saveTicket(Ticket ticket);

    Optional<Ticket> findTicket(Long id);

    List<Ticket> findAllTickets();

    boolean deleteTicketIfExists(Long id);

}
