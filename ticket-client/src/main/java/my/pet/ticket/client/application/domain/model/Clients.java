package my.pet.ticket.client.application.domain.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import my.pet.ticket.application.domain.model.Client;

@Data
@AllArgsConstructor
public class Clients {

  private List<Client> clients;

  private Long pages;

}
