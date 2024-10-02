package my.pet.ticket.client.application.port.api;

import java.util.List;
import my.pet.ticket.application.domain.model.Client;
import my.pet.ticket.application.domain.model.Clients;

public interface ClientGrpcPort {

  List<Client> getAllClients(String token);

  Clients getAllClients(String token, Integer page, Integer pageSize);

}
