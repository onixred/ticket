package my.pet.ticket.server.application.domain.service;

import java.util.List;
import my.pet.ticket.server.adapter.persistence.entity.TicketStatusEntity;
import my.pet.ticket.server.adapter.persistence.entity.TicketStatusEntity_;
import my.pet.ticket.server.application.domain.model.TicketStatus;
import my.pet.ticket.server.application.port.persistence.TicketStatusPort;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TicketStatusService implements DomainService<TicketStatus, TicketStatusEntity> {

  private static final DomainServiceException TICKET_STATUS_NOT_FOUND = new DomainServiceException(
      "Ticket status not found");

  private final TicketStatusPort ticketStatusPort;

  private final ModelMapper modelMapper;

  public TicketStatusService(TicketStatusPort ticketStatusPort, ModelMapper modelMapper) {
    this.ticketStatusPort = ticketStatusPort;
    this.modelMapper = modelMapper;
  }

  @Override
  public TicketStatus get(Long id) {
    TicketStatusEntity ticketStatusEntity = this.ticketStatusPort.get(
            ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(
                TicketStatusEntity_.TICKET_STATUS_ID), id)))
        .orElseThrow(() -> TICKET_STATUS_NOT_FOUND);
    return convertEntityToModel(ticketStatusEntity);
  }

  @Override
  public void delete(Long id) {
    TicketStatusEntity ticketStatusEntity = this.ticketStatusPort.get(
            ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(
                TicketStatusEntity_.TICKET_STATUS_ID), id)))
        .orElseThrow(() -> TICKET_STATUS_NOT_FOUND);
    this.ticketStatusPort.delete(ticketStatusEntity);
  }

  @Override
  public List<TicketStatus> getAll(Pageable pageable) {
    return this.ticketStatusPort.getAll(((root, query, criteriaBuilder) -> criteriaBuilder.equal(
            root.get(TicketStatusEntity_.ACTIVE), true)), pageable)
        .stream()
        .map(this::convertEntityToModel)
        .toList();
  }

  @Override
  public TicketStatus convertEntityToModel(TicketStatusEntity entity) {
    return this.modelMapper.map(entity, TicketStatus.class);
  }

}
