package my.pet.ticket.client.adapter.http;

import my.pet.ticket.application.domain.model.payload.request.UpdateTicketRequest;
import my.pet.ticket.client.application.domain.service.TicketsService;
import my.pet.ticket.client.application.port.entrypoint.TicketPort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class TicketsAdapter implements TicketPort {

  private final TicketsService ticketsService;

  public TicketsAdapter(TicketsService ticketsService) {
    this.ticketsService = ticketsService;
  }

  @Override
  public String tickets(Model model, String token, Long userId, String role, Long requestTicketId,
      Integer page) {
    return this.ticketsService.tickets(model, token, userId, role, requestTicketId, page);
  }

  @Override
  public ResponseEntity<String> updateTicketRequest(UpdateTicketRequest request, String token,
      Long requestTicketId, Integer page) {
    return this.ticketsService.updateTicket(request, token, requestTicketId, page);
  }

}
