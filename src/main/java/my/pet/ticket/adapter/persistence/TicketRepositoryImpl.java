package my.pet.ticket.adapter.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import my.pet.ticket.domain.Ticket;
import my.pet.ticket.port.TicketRepository;

@Repository
@RequiredArgsConstructor
public class TicketRepositoryImpl implements TicketRepository {

    private final TicketJpaRepository ticketJpaRepository;
    private final TicketEntityMapper ticketEntityMapper;

    @Override
    public Ticket save(Ticket ticket) {
        TicketEntity ticketEntity = ticketJpaRepository.save(ticketEntityMapper.toTicketEntity(ticket));
        return ticketEntityMapper.toTicket(ticketEntity);
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return ticketJpaRepository.findById(id)
                .map(ticketEntityMapper::toTicket);
    }

    @Override
    public List<Ticket> findAll() {
        return ticketJpaRepository.findAll().stream()
                .map(ticketEntityMapper::toTicket)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        ticketJpaRepository.deleteById(id);
    }

}
