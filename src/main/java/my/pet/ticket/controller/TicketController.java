package my.pet.ticket.controller;


import lombok.RequiredArgsConstructor;
import my.pet.ticket.model.dto.TicketForCreateDto;
import my.pet.ticket.model.dto.TicketResponseDto;
import my.pet.ticket.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/ticket")
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    public List<TicketResponseDto> findAllTickets() {
        return ticketService.findAllTickets();
    }

    @GetMapping("/{id}")
    public TicketResponseDto findTicketById(@PathVariable("id") Long id) {
        return ticketService.findTicketById(id);
    }

    @PostMapping
    public void createTicket(@RequestBody TicketForCreateDto ticketForCreateDto) {
        ticketService.createTicket(ticketForCreateDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable("id") Long id) {
        ticketService.deleteTicket(id);
    }
}
