package my.pet.ticket.client.adapter.http;

import my.pet.ticket.client.application.domain.service.CabinetService;
import my.pet.ticket.client.application.port.entrypoint.CabinetPort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class CabinetAdapter implements CabinetPort {

  private final CabinetService cabinetService;

  public CabinetAdapter(CabinetService cabinetService) {
    this.cabinetService = cabinetService;
  }

  @Override
  public String cabinet(Model model, String token, Long userId, String role) {
    return this.cabinetService.cabinet(model, token, userId, role);

  }

}
