package my.pet.ticket.client.application.domain.service;

import java.util.List;
import my.pet.ticket.application.domain.model.Ticket;
import my.pet.ticket.client.adapter.grpc.TicketGrpcAdapter;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class TicketsService {

  private final TicketGrpcAdapter ticketGrpcAdapter;

  public TicketsService(TicketGrpcAdapter ticketGrpcAdapter) {
    this.ticketGrpcAdapter = ticketGrpcAdapter;
  }

  public String tickets(Model model, String token, Long userId, String role, Long requestTicketId) {
    List<Ticket> tickets = this.ticketGrpcAdapter.getAllTickets(token);
    model.addAttribute("tickets", tickets);
    if (requestTicketId != null) {
      tickets.stream().filter(ticket -> ticket.getTicketId().equals(requestTicketId)).findFirst()
          .ifPresent(ticket -> model.addAttribute("requestTicket", ticket));
    }
    return "tickets.html";
  }

}
