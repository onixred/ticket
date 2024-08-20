package my.pet.ticket.service;

import my.pet.ticket.model.dto.TicketForCreateDto;
import my.pet.ticket.model.dto.TicketResponseDto;
import my.pet.ticket.model.entity.Ticket;

import java.util.List;

public interface TicketService {

    List<TicketResponseDto> findAllTickets();

    TicketResponseDto findTicketById(Long id);

    void createTicket(TicketForCreateDto ticketForCreateDto);

    void deleteTicket(Long id);
}
