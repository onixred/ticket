package my.pet.ticket.client.application.port.entrypoint;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface RegistrationPort {

  @RequestMapping("/registration")
  String register(Model model,
      @RequestParam(name = "failure", required = false, defaultValue = "false") Boolean failure);

  @RequestMapping("/registration.html")
  default String register() {
    return "redirect:/registration";
  }

}
