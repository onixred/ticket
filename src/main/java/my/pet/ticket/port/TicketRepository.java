package my.pet.ticket.port;

import java.util.List;
import java.util.Optional;

import my.pet.ticket.domain.Ticket;

public interface TicketRepository {

    Ticket save(Ticket ticket);

    Optional<Ticket> findById(Long id);

    List<Ticket> findAll();

    void deleteById(Long id);

}
