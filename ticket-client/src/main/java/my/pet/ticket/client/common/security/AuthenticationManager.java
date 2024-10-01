package my.pet.ticket.client.common.security;

import my.pet.ticket.client.adapter.grpc.LoginGrpcAdapter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Aspect
@Component
public class AuthenticationManager {

  private final LoginGrpcAdapter loginGrpcAdapter;

  public AuthenticationManager(LoginGrpcAdapter loginGrpcAdapter) {
    this.loginGrpcAdapter = loginGrpcAdapter;
  }

  @Pointcut("execution(public String my.pet.ticket.client.adapter.http.**.**(..))")
  public void httpAdapters() {
  }

  @Pointcut("execution(public String my.pet.ticket.client.adapter.http.Log*.**(..))")
  public void loginLogoutAdapters() {
  }

  @Pointcut("execution(public String my.pet.ticket.client.adapter.http.Reg*.**(..))")
  public void registrationAdapters() {
  }

  @Pointcut("execution(public String my.pet.ticket.client.adapter.http.Index*.**(..))")
  public void indexAdapters() {
  }

  @Around("httpAdapters() && !loginLogoutAdapters() && !registrationAdapters() && !indexAdapters()")
  public Object aroundHttpAdapters(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    Object[] args = proceedingJoinPoint.getArgs();
    if (args.length >= 2 && args[0] != null && args[0] instanceof Model model) {
      if (args[1] != null && args[1] instanceof String token) {
        if (!this.loginGrpcAdapter.isTokenExpired(token)) {
          model.addAttribute("error", true);
          model.addAttribute("error_message", "Session is expired. Please login again");
          return "redirect:/login";
        }
      } else {
        model.addAttribute("error", true);
        model.addAttribute("error_message", "Not authorized");
        return "redirect:/login";
      }
    }
    return proceedingJoinPoint.proceed();
  }

}
