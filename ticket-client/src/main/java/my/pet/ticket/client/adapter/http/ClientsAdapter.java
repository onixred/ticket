package my.pet.ticket.client.adapter.http;

import my.pet.ticket.client.application.port.entrypoint.ClientsPort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class ClientsAdapter implements ClientsPort {

  @Override
  public String clients(Model model, String token, Long userId, String role) {
    return "clients.html";
  }

}
