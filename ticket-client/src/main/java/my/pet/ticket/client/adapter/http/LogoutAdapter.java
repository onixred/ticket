package my.pet.ticket.client.adapter.http;

import my.pet.ticket.client.application.port.entrypoint.LogoutPort;
import org.springframework.stereotype.Controller;

@Controller
public class LogoutAdapter implements LogoutPort {

  @Override
  public String logout() {
    return "logout.html";
  }

}
