package my.pet.ticket.application.domain.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Users {

  private List<User> users;

  private Long pages;

}
