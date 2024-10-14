package my.pet.ticket.application.domain.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Clients {

  private List<Client> clients;

  private Long pages;

}
