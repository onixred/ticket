package my.pet.ticket.adapter.out.persistence;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import my.pet.ticket.adapter.mapper.AbstractMapper;
import my.pet.ticket.adapter.out.persistence.entity.TicketEntity;
import my.pet.ticket.application.model.Ticket;
import my.pet.ticket.application.port.out.persistence.TicketPersistencePort;

@Component
public class TicketPersistenceAdapter extends AbstractMapper implements TicketPersistencePort {

    private final TicketJpaAdapter ticketJpaAdapter;

    TicketPersistenceAdapter(TicketJpaAdapter ticketJpaAdapter, ModelMapper modelMapper) {
        super(modelMapper);
        this.ticketJpaAdapter = ticketJpaAdapter;
    }

    @Override
    public Ticket save(Ticket ticket) {
        TicketEntity ticketEntity = ticketJpaAdapter.save(map(ticket, TicketEntity.class));
        return map(ticketEntity, Ticket.class);
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return ticketJpaAdapter.findById(id)
                .map(entity -> map(entity, Ticket.class));
    }

    @Override
    public List<Ticket> findAll() {
        return ticketJpaAdapter.findAll().stream()
                .map(entity -> map(entity, Ticket.class))
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        ticketJpaAdapter.deleteById(id);
    }

}
