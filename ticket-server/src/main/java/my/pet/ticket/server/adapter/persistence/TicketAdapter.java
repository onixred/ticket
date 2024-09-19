package my.pet.ticket.server.adapter.persistence;

import my.pet.ticket.server.adapter.persistence.entity.TicketEntity;
import my.pet.ticket.server.adapter.persistence.repository.TicketRepository;
import my.pet.ticket.server.application.port.persistence.TicketPort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TicketAdapter implements TicketPort {

    private final TicketRepository ticketRepository;

    public TicketAdapter(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Optional<TicketEntity> get(Specification<TicketEntity> specification) {
        return this.ticketRepository.findOne(specification);
    }

    @Override
    public List<TicketEntity> getAll(Specification<TicketEntity> specification, Pageable pageable) {
        return this.ticketRepository.findAll(specification, pageable).stream().toList();
    }

    @Override
    public TicketEntity create(TicketEntity entity) {
        if (entity.getId().getTicketId() == null) {
            return this.ticketRepository.save(entity);
        }
        throw new PersistenceAdapterException("Ticket shouldn't have id when creating");
    }

    @Override
    public TicketEntity update(TicketEntity entity) {
        if (this.ticketRepository.existsById(entity.getId())) {
            return this.ticketRepository.save(entity);
        }
        throw new PersistenceAdapterException("Ticket not exist");
    }

    @Override
    public void delete(TicketEntity entity) {
        if (this.ticketRepository.existsById(entity.getId())) {
            entity.setDeleted(true);
            this.ticketRepository.save(entity);
        }
        throw new PersistenceAdapterException("Ticket not exist");
    }

}
