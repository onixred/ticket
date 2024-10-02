package my.pet.ticket.client.application.port.entrypoint;

import my.pet.ticket.application.domain.model.payload.request.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface LoginPort {

  @RequestMapping("/login")
  String login(Model model,
      @RequestParam(name = "failure", required = false, defaultValue = "false") Boolean failure,
      @RequestParam(name = "logout", required = false, defaultValue = "false") Boolean logout);

  @RequestMapping("/loginRequest")
  ResponseEntity<String> loginRequest(@ModelAttribute LoginRequest loginRequest);

  @RequestMapping("/login.html")
  default String login() {
    return "redirect:/login";
  }

}
