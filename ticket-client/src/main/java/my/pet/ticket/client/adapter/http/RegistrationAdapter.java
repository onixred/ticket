package my.pet.ticket.client.adapter.http;

import my.pet.ticket.client.application.port.entrypoint.RegistrationPort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class RegistrationAdapter implements RegistrationPort {

  @Override
  public String register(Model model, Boolean failure) {
    return "registration.html";
  }

}
