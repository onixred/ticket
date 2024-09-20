package my.pet.ticket.server.adapter.persistence;

import my.pet.ticket.server.adapter.persistence.entity.TicketStatusEntity;
import my.pet.ticket.server.adapter.persistence.repository.TicketStatusRepository;
import my.pet.ticket.server.application.port.persistence.TicketStatusPort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TicketStatusAdapter
        implements TicketStatusPort {

    private final TicketStatusRepository ticketStatusRepository;

    public TicketStatusAdapter (TicketStatusRepository ticketStatusRepository) {
        this.ticketStatusRepository = ticketStatusRepository;
    }

    @Override
    public Optional<TicketStatusEntity> get (Specification<TicketStatusEntity> specification) {
        return this.ticketStatusRepository.findOne(specification);
    }

    @Override
    public List<TicketStatusEntity> getAll (Specification<TicketStatusEntity> specification, Pageable pageable) {
        return this.ticketStatusRepository.findAll(specification, pageable).stream().toList();
    }

    @Override
    public TicketStatusEntity create (TicketStatusEntity entity) {
        if(entity.getTicketStatusId() == null) {
            return this.ticketStatusRepository.save(entity);
        }
        throw new PersistenceAdapterException("Ticket status shouldn't have id when creating");
    }

    @Override
    public TicketStatusEntity update (TicketStatusEntity entity) {
        if(this.ticketStatusRepository.existsById(entity.getTicketStatusId())) {
            return this.ticketStatusRepository.save(entity);
        }
        throw new PersistenceAdapterException("Ticket status not exist");
    }

}
