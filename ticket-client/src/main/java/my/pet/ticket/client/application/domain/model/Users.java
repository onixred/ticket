package my.pet.ticket.client.application.domain.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import my.pet.ticket.application.domain.model.User;

@Data
@AllArgsConstructor
public class Users {

  private List<User> users;

  private Long pages;

}
