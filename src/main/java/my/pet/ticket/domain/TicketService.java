package my.pet.ticket.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import my.pet.ticket.port.TicketRepository;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Optional<Ticket> findTicket(Long id) {
        return ticketRepository.findById(id);
    }

    public List<Ticket> findAllTickets() {
        return ticketRepository.findAll();
    }

    public boolean deleteTicketIfExists(Long id) {
        if (ticketRepository.findById(id).isPresent()) {
            ticketRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
