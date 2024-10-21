package my.pet.ticket.application.port.out.persistence;

import java.util.List;
import java.util.Optional;

import my.pet.ticket.application.model.Ticket;

public interface TicketPersistencePort {

    Ticket save(Ticket ticket);

    Optional<Ticket> findById(Long id);

    List<Ticket> findAll();

    void deleteById(Long id);

}
