package my.pet.ticket.application;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import my.pet.ticket.application.model.Ticket;
import my.pet.ticket.application.port.out.persistence.TicketPersistencePort;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketPersistencePort ticketPersistencePort;

    @Override
    public Ticket saveTicket(Ticket ticket) {
        return ticketPersistencePort.save(ticket);
    }

    @Override
    public Optional<Ticket> findTicket(Long id) {
        return ticketPersistencePort.findById(id);
    }

    @Override
    public List<Ticket> findAllTickets() {
        return ticketPersistencePort.findAll();
    }

    @Override
    public boolean deleteTicketIfExists(Long id) {
        if (ticketPersistencePort.findById(id).isPresent()) {
            ticketPersistencePort.deleteById(id);
            return true;
        }
        return false;
    }

}
