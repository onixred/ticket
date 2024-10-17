package my.pet.ticket.adapter.web;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import my.pet.ticket.adapter.AbstractMapper;
import my.pet.ticket.domain.Ticket;
import my.pet.ticket.domain.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketController extends AbstractMapper {

    private final TicketService ticketService;

    TicketController(TicketService ticketService, ModelMapper modelMapper) {
        super(modelMapper);
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<TicketDto> createTicket(@Valid @RequestBody TicketDto ticketDto) {
        Ticket ticket = ticketService.saveTicket(map(ticketDto, Ticket.class));
        return ResponseEntity.ok(map(ticket, TicketDto.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDto> getTicket(@PathVariable Long id) {
        return ticketService.findTicket(id)
                .map(ticket -> map(ticket, TicketDto.class))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TicketDto>> getTickets() {
        List<TicketDto> ticketDtoList = ticketService.findAllTickets().stream()
                .map(ticket -> map(ticket, TicketDto.class))
                .toList();
        return ResponseEntity.ok(ticketDtoList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        return ticketService.deleteTicketIfExists(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.noContent().build();
    }

}
