package my.pet.ticket.client.adapter.http;

import my.pet.ticket.client.adapter.http.exception.LoginAdapterException;
import my.pet.ticket.client.adapter.http.exception.UserAdapterException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HttpAdapterAdvice {

  @ExceptionHandler(LoginAdapterException.class)
  public String loginAdapterExceptionHandler(LoginAdapterException exception, Model model) {
    model.addAttribute("error", true);
    model.addAttribute("error_message", exception.getMessage());
    return "login";
  }

  @ExceptionHandler(UserAdapterException.class)
  public String userAdapterRuntimeExceptionHandler(UserAdapterException exception, Model model) {
    model.addAttribute("error", true);
    model.addAttribute("error_message", exception.getCause().getMessage());
    return "user";
  }

}
