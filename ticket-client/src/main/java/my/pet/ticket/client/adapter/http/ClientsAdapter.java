package my.pet.ticket.client.adapter.http;

import my.pet.ticket.client.application.domain.service.ClientsService;
import my.pet.ticket.client.application.port.entrypoint.ClientsPort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class ClientsAdapter implements ClientsPort {

  private final ClientsService clientsService;

  public ClientsAdapter(ClientsService clientsService) {
    this.clientsService = clientsService;
  }

  @Override
  public String clients(Model model, String token, Long userId, String role, Long requestClientId) {
    return this.clientsService.clients(model, token, userId, role, requestClientId);
  }

}
