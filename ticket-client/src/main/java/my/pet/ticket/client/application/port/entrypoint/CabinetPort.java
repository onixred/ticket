package my.pet.ticket.client.application.port.entrypoint;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

public interface CabinetPort {

  @RequestMapping("/cabinet")
  String cabinet(Model model, @CookieValue(value = "Authorization", required = false) String token,
      @CookieValue(name = "UserId", required = false) Long userId,
      @CookieValue(name = "Role", required = false) String role);

  @RequestMapping("/cabinet.html")
  default String cabinet() {
    return "redirect:/cabinet";
  }

}
