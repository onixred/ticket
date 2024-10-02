package my.pet.ticket.client.application.domain.service;

import my.pet.ticket.client.application.domain.model.Clients;
import my.pet.ticket.client.application.port.api.ClientGrpcPort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class ClientsService {

  private final ClientGrpcPort clientGrpcPort;

  public ClientsService(ClientGrpcPort clientGrpcPort) {
    this.clientGrpcPort = clientGrpcPort;
  }

  public String clients(Model model, String token, Long clientId, String role,
      Long requestClientId, Integer page) {
    Clients clients = this.clientGrpcPort.getAllClients(token, page, 4);
    model.addAttribute("clients", clients.getClients());
    model.addAttribute("pages", clients.getPages());
    model.addAttribute("page", page != null ? page : 0);
    if (requestClientId != null) {
      clients.getClients().stream().filter(client -> client.getClientId().equals(requestClientId))
          .findFirst()
          .ifPresent(client -> model.addAttribute("requestClient", client));
    }
    return "clients.html";
  }

}
