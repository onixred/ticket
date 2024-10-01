package my.pet.ticket.server.application.domain.service;

import java.util.List;
import my.pet.ticket.application.domain.model.Client;
import my.pet.ticket.application.domain.model.Ticket;
import my.pet.ticket.application.domain.model.TicketStatus;
import my.pet.ticket.application.domain.model.User;
import my.pet.ticket.server.adapter.persistence.entity.TicketEntity;
import my.pet.ticket.server.adapter.persistence.entity.TicketEntity_;
import my.pet.ticket.server.adapter.persistence.entity.TicketIdEntity_;
import my.pet.ticket.server.application.port.persistence.TicketPort;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketService implements DomainService<Ticket, TicketEntity> {

  private static final DomainServiceException TICKET_NOT_FOUND = new DomainServiceException(
      "Ticket not found");

  private final TicketPort ticketPort;

  private final TicketStatusService ticketStatusService;

  private final UserService userService;

  private final ClientService clientService;

  private final ModelMapper modelMapper;

  public TicketService(TicketPort ticketPort, TicketStatusService ticketStatusService,
      UserService userService, ClientService clientService,
      ModelMapper modelMapper) {
    this.ticketPort = ticketPort;
    this.ticketStatusService = ticketStatusService;
    this.userService = userService;
    this.clientService = clientService;
    this.modelMapper = modelMapper;
  }

  @Override
  @Transactional
  public Ticket get(Long id) {
    TicketEntity ticketEntity = this.ticketPort.get(
            ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(
                TicketEntity_.ID).get(TicketIdEntity_.TICKET_ID), id)))
        .orElseThrow(() -> TICKET_NOT_FOUND);
    return convertEntityToModel(ticketEntity);
  }

  @Override
  @Transactional
  public List<Ticket> getAll() {
    return DomainService.super.getAll();
  }

  @Override
  @Transactional
  public List<Ticket> getAll(Integer page, Integer pageSize) {
    return DomainService.super.getAll(page, pageSize);
  }

  @Transactional
  public Ticket getByClientId(Long id) {
    TicketEntity ticketEntity = this.ticketPort.get(
            ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(
                TicketEntity_.ID).get(TicketIdEntity_.CLIENT_ID), id)))
        .orElseThrow(() -> TICKET_NOT_FOUND);
    return convertEntityToModel(ticketEntity);
  }

  @Override
  @Transactional
  public void delete(Long id) {
    TicketEntity ticketEntity = this.ticketPort.get(
            ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(
                TicketEntity_.ID).get(TicketIdEntity_.TICKET_ID), id)))
        .orElseThrow(() -> TICKET_NOT_FOUND);
    this.ticketPort.delete(ticketEntity);
  }

  @Transactional
  public void deleteByClientId(Long id) {
    TicketEntity ticketEntity = this.ticketPort.get(
            ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(
                TicketEntity_.ID).get(TicketIdEntity_.CLIENT_ID), id)))
        .orElseThrow(() -> TICKET_NOT_FOUND);
    this.ticketPort.delete(ticketEntity);
  }

  @Override
  @Transactional
  public List<Ticket> getAll(Pageable pageable) {
    return this.ticketPort.getAll((root, query, criteriaBuilder) -> criteriaBuilder.conjunction(),
            pageable)
        .stream()
        .map(this::convertEntityToModel)
        .toList();
  }

  @Override
  public Ticket convertEntityToModel(TicketEntity entity) {
    TicketStatus ticketStatus = this.ticketStatusService.get(entity.getTicketStatusId());
    Client client = this.clientService.get(entity.getId().getClientId());
    User author = this.userService.get(entity.getAuthorId());
    User manager = this.userService.get(entity.getManagerId());
    Ticket ticket = Ticket.builder()
        .ticketId(entity.getId().getTicketId())
        .title(entity.getTitle())
        .description(entity.getDescription())
        .build();
    ticket.setTicketStatus(ticketStatus);
    ticket.setClient(client);
    ticket.setAuthor(author);
    ticket.setManager(manager);
    return ticket;
  }

}
