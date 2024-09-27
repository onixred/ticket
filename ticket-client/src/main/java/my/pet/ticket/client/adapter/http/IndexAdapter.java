package my.pet.ticket.client.adapter.http;

import my.pet.ticket.client.application.port.entrypoint.IndexPort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class IndexAdapter implements IndexPort {

  @Override
  public String index(Model model, String token, Long userId, String role) {
    model.addAttribute("role", role);
    return "index.html";
  }

}
