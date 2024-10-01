package my.pet.ticket.server.adapter.persistence;

import java.util.Optional;
import my.pet.ticket.server.adapter.persistence.entity.TicketStatusEntity;
import my.pet.ticket.server.adapter.persistence.entity.TicketStatusEntity_;
import my.pet.ticket.server.adapter.persistence.repository.TicketStatusRepository;
import my.pet.ticket.server.application.port.persistence.TicketStatusPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class TicketStatusAdapter implements TicketStatusPort {

  private static final Specification<TicketStatusEntity> NOT_DELETED_SPECIFICATION = ((root, query, criteriaBuilder) -> criteriaBuilder.equal(
      root.get(TicketStatusEntity_.DELETED), false));

  private final TicketStatusRepository ticketStatusRepository;

  public TicketStatusAdapter(TicketStatusRepository ticketStatusRepository) {
    this.ticketStatusRepository = ticketStatusRepository;
  }

  @Override
  public Optional<TicketStatusEntity> get(Specification<TicketStatusEntity> specification) {
    return this.ticketStatusRepository.findOne(NOT_DELETED_SPECIFICATION.and(specification));
  }

  @Override
  public Page<TicketStatusEntity> getAll(Specification<TicketStatusEntity> specification,
      Pageable pageable) {
    return this.ticketStatusRepository.findAll(NOT_DELETED_SPECIFICATION.and(specification),
        pageable);
  }

  @Override
  public TicketStatusEntity create(TicketStatusEntity entity) {
    if (entity.getTicketStatusId() == null) {
      return this.ticketStatusRepository.save(entity);
    }
    throw new PersistenceAdapterException("Ticket status shouldn't have id when creating");
  }

  @Override
  public TicketStatusEntity update(TicketStatusEntity entity) {
    if (this.ticketStatusRepository.existsById(entity.getTicketStatusId())) {
      return this.ticketStatusRepository.save(entity);
    }
    throw new PersistenceAdapterException("Ticket status not exist");
  }

}
