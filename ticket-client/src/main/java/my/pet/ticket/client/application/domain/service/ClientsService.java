package my.pet.ticket.client.application.domain.service;

import java.util.List;
import my.pet.ticket.application.domain.model.Client;
import my.pet.ticket.client.application.port.api.ClientGrpcPort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class ClientsService {

  private final ClientGrpcPort clientGrpcPort;

  public ClientsService(ClientGrpcPort clientGrpcPort) {
    this.clientGrpcPort = clientGrpcPort;
  }

  public String clients(Model model, String token, Long clientId, String role) {
    List<Client> clients = this.clientGrpcPort.getAllClients(token);
    model.addAttribute("clients", clients);
    return "clients.html";
  }

}
