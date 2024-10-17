package my.pet.ticket.adapter.persistence;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import my.pet.ticket.adapter.AbstractMapper;
import my.pet.ticket.domain.Ticket;
import my.pet.ticket.port.TicketRepository;

@Repository
public class TicketRepositoryImpl extends AbstractMapper implements TicketRepository {

    private final TicketJpaRepository ticketJpaRepository;

    TicketRepositoryImpl(TicketJpaRepository ticketJpaRepository, ModelMapper modelMapper) {
        super(modelMapper);
        this.ticketJpaRepository = ticketJpaRepository;
    }

    @Override
    public Ticket save(Ticket ticket) {
        TicketEntity ticketEntity = ticketJpaRepository.save(map(ticket, TicketEntity.class));
        return map(ticketEntity, Ticket.class);
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return ticketJpaRepository.findById(id)
                .map(entity -> map(entity, Ticket.class));
    }

    @Override
    public List<Ticket> findAll() {
        return ticketJpaRepository.findAll().stream()
                .map(entity -> map(entity, Ticket.class))
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        ticketJpaRepository.deleteById(id);
    }

}
