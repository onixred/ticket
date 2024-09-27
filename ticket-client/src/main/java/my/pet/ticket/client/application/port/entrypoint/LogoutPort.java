package my.pet.ticket.client.application.port.entrypoint;

import org.springframework.web.bind.annotation.RequestMapping;

public interface LogoutPort {

  @RequestMapping("/logout")
  String logout();

  @RequestMapping("/logout.html")
  default String logoutExt() {
    return "redirect:/logout";
  }

}
