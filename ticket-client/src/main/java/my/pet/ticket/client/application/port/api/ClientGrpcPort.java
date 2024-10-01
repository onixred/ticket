package my.pet.ticket.client.application.port.api;

import java.util.List;
import my.pet.ticket.application.domain.model.Client;

public interface ClientGrpcPort {

  List<Client> getAllClients(String token);

}
