package my.pet.ticket.client.adapter.http;

import my.pet.ticket.client.application.domain.service.TicketsService;
import my.pet.ticket.client.application.port.entrypoint.TicketPort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class TicketsAdapter implements TicketPort {

  private final TicketsService ticketsService;

  public TicketsAdapter(TicketsService ticketsService) {
    this.ticketsService = ticketsService;
  }

  @Override
  public String tickets(Model model, String token, Long userId, String role, Long requestTicketId) {
    return this.ticketsService.tickets(model, token, userId, role, requestTicketId);
  }

}