package my.pet.ticket.client.adapter.http;

import my.pet.ticket.client.application.domain.service.PersonalAccountService;
import my.pet.ticket.client.application.port.entrypoint.PersonalAccountPort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class PersonalAccountAdapter implements PersonalAccountPort {

  private final PersonalAccountService personalAccountService;

  public PersonalAccountAdapter(PersonalAccountService personalAccountService) {
    this.personalAccountService = personalAccountService;
  }

  @Override
  public String cabinet(Model model, String token, Long userId, String role) {
    return this.personalAccountService.personalAccount(model, token, userId, role);
  }

}
