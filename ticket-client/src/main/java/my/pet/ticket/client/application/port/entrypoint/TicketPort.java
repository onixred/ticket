package my.pet.ticket.client.application.port.entrypoint;

import my.pet.ticket.application.domain.model.payload.request.UpdateTicketRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface TicketPort {

  @RequestMapping("/tickets")
  String tickets(Model model, @CookieValue(value = "Authorization", required = false) String token,
      @CookieValue(name = "UserId", required = false) Long userId,
      @CookieValue(name = "Role", required = false) String role,
      @RequestParam(name = "id", required = false) Long requestTicketId,
      @RequestParam(name = "page", required = false, defaultValue = "0") Integer page);

  @PostMapping("/updateTicketRequest")
  ResponseEntity<String> updateTicketRequest(
      @ModelAttribute UpdateTicketRequest request,
      @CookieValue(value = "Authorization", required = false) String token,
      @RequestParam(name = "id", required = false) Long requestTicketId,
      @RequestParam(name = "page", required = false, defaultValue = "0") Integer page
  );

  @RequestMapping("/tickets.html")
  default String ticketsExt() {
    return "redirect:/tickets";
  }

}
