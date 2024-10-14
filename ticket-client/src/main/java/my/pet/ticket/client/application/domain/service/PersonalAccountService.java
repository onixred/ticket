package my.pet.ticket.client.application.domain.service;

import java.util.List;
import my.pet.ticket.application.domain.model.Ticket;
import my.pet.ticket.application.domain.model.User;
import my.pet.ticket.client.application.port.api.TicketGrpcPort;
import my.pet.ticket.client.application.port.api.UserGrpcPort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class PersonalAccountService {

  private final UserGrpcPort userGrpcPort;

  private final TicketGrpcPort ticketGrpcPort;

  public PersonalAccountService(UserGrpcPort userGrpcPort, TicketGrpcPort ticketGrpcPort) {
    this.userGrpcPort = userGrpcPort;
    this.ticketGrpcPort = ticketGrpcPort;
  }

  public String personalAccount(Model model, String token, Long userId, String role) {
    model.addAttribute("role", role);
    User user = this.userGrpcPort.getUser(userId, token);
    model.addAttribute("user", user);
    List<Ticket> tickets = this.ticketGrpcPort.getAllTickets(token)
        .stream()
        .filter(ticket -> ticket.getAuthor().getUserId().equals(userId))
        .toList();
    model.addAttribute("activeTickets", tickets);
    return "personal-account.html";
  }

}
