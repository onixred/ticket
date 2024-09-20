package my.pet.ticket.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticationController {

  @GetMapping("/login")
  public String login(
      @RequestParam(name = "loginRequired", required = false) final Boolean loginRequired,
      @RequestParam(name = "loginError", required = false) final Boolean loginError,
      @RequestParam(name = "logoutSuccess", required = false) final Boolean logoutSuccess,
      final Model model) {
    model.addAttribute("test", "test");
    return "authentication/login";
  }

}
