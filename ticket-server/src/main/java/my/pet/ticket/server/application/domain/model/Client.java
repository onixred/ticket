package my.pet.ticket.server.application.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {

  private Long clientId;

  private String fullName;

  private String email;

  private String phoneNumber;

}
