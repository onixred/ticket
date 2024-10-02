package my.pet.ticket.client.application.domain.service;

import my.pet.ticket.application.domain.model.Tickets;
import my.pet.ticket.application.domain.model.payload.request.UpdateTicketRequest;
import my.pet.ticket.client.adapter.grpc.TicketGrpcAdapter;
import my.pet.ticket.client.adapter.grpc.TicketStatusGrpcService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class TicketsService {

  private final TicketGrpcAdapter ticketGrpcAdapter;

  private final TicketStatusGrpcService ticketStatusGrpcService;

  public TicketsService(TicketGrpcAdapter ticketGrpcAdapter,
      TicketStatusGrpcService ticketStatusGrpcService) {
    this.ticketGrpcAdapter = ticketGrpcAdapter;
    this.ticketStatusGrpcService = ticketStatusGrpcService;
  }

  public String tickets(Model model, String token, Long userId, String role, Long requestTicketId,
      Integer page) {
    Tickets tickets = this.ticketGrpcAdapter.getAllTickets(token, page, 4);
    model.addAttribute("tickets", tickets.getTickets());
    model.addAttribute("pages", tickets.getPages());
    model.addAttribute("page", page != null ? page : 0);
    model.addAttribute("ticketStatuses", this.ticketStatusGrpcService.getAllTicketStatuses(token));
    if (requestTicketId != null) {
      tickets.getTickets().stream().filter(ticket -> ticket.getTicketId().equals(requestTicketId))
          .findFirst()
          .ifPresent(ticket -> model.addAttribute("requestTicket", ticket));
    }
    return "tickets.html";
  }

  public ResponseEntity<String> updateTicket(UpdateTicketRequest request, String token,
      Long requestTicketId, Integer page) {
    request.setTicketId(requestTicketId);
    this.ticketGrpcAdapter.updateTicket(token, request);
    return ResponseEntity.status(302)
        .header(HttpHeaders.LOCATION, "/tickets?id=" + requestTicketId + "&page=" + page)
        .build();
  }
}
