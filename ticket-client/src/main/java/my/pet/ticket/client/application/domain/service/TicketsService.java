package my.pet.ticket.client.application.domain.service;

import my.pet.ticket.client.adapter.grpc.TicketGrpcAdapter;
import my.pet.ticket.client.application.domain.model.Tickets;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class TicketsService {

  private final TicketGrpcAdapter ticketGrpcAdapter;

  public TicketsService(TicketGrpcAdapter ticketGrpcAdapter) {
    this.ticketGrpcAdapter = ticketGrpcAdapter;
  }

  public String tickets(Model model, String token, Long userId, String role, Long requestTicketId,
      Integer page) {
    Tickets tickets = this.ticketGrpcAdapter.getAllTickets(token, page, 4);
    model.addAttribute("tickets", tickets.getTickets());
    model.addAttribute("pages", tickets.getPages());
    model.addAttribute("page", page != null ? page : 0);
    if (requestTicketId != null) {
      tickets.getTickets().stream().filter(ticket -> ticket.getTicketId().equals(requestTicketId))
          .findFirst()
          .ifPresent(ticket -> model.addAttribute("requestTicket", ticket));
    }
    return "tickets.html";
  }

}
