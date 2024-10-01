package my.pet.ticket.client.application.domain.service;

import java.util.List;
import my.pet.ticket.client.adapter.grpc.TicketGrpcAdapter;
import my.pet.ticket.client.application.domain.model.Ticket;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class TicketsService {

  private final TicketGrpcAdapter ticketGrpcAdapter;

  public TicketsService(TicketGrpcAdapter ticketGrpcAdapter) {
    this.ticketGrpcAdapter = ticketGrpcAdapter;
  }

  public String tickets(Model model, String token, Long userId, String role) {
    List<Ticket> ticketList = this.ticketGrpcAdapter.getAllTickets(token);
    model.addAttribute("tickets", ticketList);
    return "tickets.html";
  }

}
