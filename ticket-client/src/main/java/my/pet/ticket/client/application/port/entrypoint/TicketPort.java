package my.pet.ticket.client.application.port.entrypoint;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface TicketPort {

  @RequestMapping("/tickets")
  String tickets(Model model, @CookieValue(value = "Authorization", required = false) String token,
      @CookieValue(name = "UserId", required = false) Long userId,
      @CookieValue(name = "Role", required = false) String role,
      @RequestParam(name = "id", required = false) Long requestTicketId,
      @RequestParam(name = "page", required = false, defaultValue = "0") Integer page);

  @RequestMapping("/tickets.html")
  default String ticketsExt() {
    return "redirect:/tickets";
  }

}
