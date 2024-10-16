package my.pet.ticket.adapter.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import my.pet.ticket.domain.Ticket;
import my.pet.ticket.domain.TicketService;

@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private final TicketDtoMapper ticketDtoMapper;

    @PostMapping
    public ResponseEntity<TicketDto> createTicket(@Valid @RequestBody TicketDto ticketDto) {
        Ticket ticket = ticketService.saveTicket(ticketDtoMapper.toTicket(ticketDto));
        return ResponseEntity.ok(ticketDtoMapper.toTicketDto(ticket));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDto> getTicket(@PathVariable Long id) {
        return ticketService.findTicket(id)
                .map(ticketDtoMapper::toTicketDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TicketDto>> getTickets() {
        List<TicketDto> ticketDtoList = ticketService.findAllTickets().stream()
                .map(ticketDtoMapper::toTicketDto)
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
