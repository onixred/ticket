package my.pet.ticket.server.adapter.persistence;

import java.util.Optional;
import my.pet.ticket.server.adapter.persistence.entity.TicketEntity;
import my.pet.ticket.server.adapter.persistence.entity.TicketEntity_;
import my.pet.ticket.server.adapter.persistence.entity.TicketIdEntity_;
import my.pet.ticket.server.adapter.persistence.repository.TicketRepository;
import my.pet.ticket.server.application.port.persistence.TicketPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class TicketAdapter implements TicketPort {

  private static final Specification<TicketEntity> NOT_DELETED_SPECIFICATION = ((root, query, criteriaBuilder) -> {
    query.orderBy(criteriaBuilder.asc(root.get(TicketEntity_.ID).get(TicketIdEntity_.TICKET_ID)));
    return criteriaBuilder.equal(root.get(TicketEntity_.DELETED), false);
  });

  private final TicketRepository ticketRepository;

  public TicketAdapter(TicketRepository ticketRepository) {
    this.ticketRepository = ticketRepository;
  }

  @Override
  public Optional<TicketEntity> get(Specification<TicketEntity> specification) {
    return this.ticketRepository.findOne(NOT_DELETED_SPECIFICATION.and(specification));
  }

  @Override
  public Page<TicketEntity> getAll(Specification<TicketEntity> specification, Pageable pageable) {
    return this.ticketRepository.findAll(NOT_DELETED_SPECIFICATION.and(specification), pageable);
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

}
