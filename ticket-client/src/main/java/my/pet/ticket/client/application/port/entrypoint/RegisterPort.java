package my.pet.ticket.client.application.port.entrypoint;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface RegisterPort {

  @RequestMapping("/register")
  String register(Model model,
      @RequestParam(name = "failure", required = false, defaultValue = "false") Boolean failure);

  @RequestMapping("/register.html")
  default String register() {
    return "redirect:/register";
  }

}
