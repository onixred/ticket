package my.pet.ticket.converter;

import my.pet.ticket.dataLayer.model.Ticket;
import my.pet.ticket.dataLayer.model.dto.TicketDTO;

public class TicketConverter {
    // Преобразование Ticket в TicketDTO
    private TicketDTO convertToDTO(Ticket ticket) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(ticket.getId());
        ticketDTO.setTaskName(ticket.getTaskName());
        ticketDTO.setDescription(ticket.getDescription());
        ticketDTO.setStatus(ticket.getStatus());
        ticketDTO.setAuthor(UserConverter.convertToDTO(ticket.getAuthor()));
        ticketDTO.setExecutor(UserConverter.convertToDTO(ticket.getExecutor()));
        return ticketDTO;
    }

    // Преобразование TicketDTO в Ticket
    private Ticket convertToEntity(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setId(ticketDTO.getId());
        ticket.setTaskName(ticketDTO.getTaskName());
        ticket.setDescription(ticketDTO.getDescription());
        ticket.setStatus(ticketDTO.getStatus());
        ticket.setAuthor(UserConverter.convertToEntity(ticketDTO.getAuthor()));
        ticket.setExecutor(UserConverter.convertToEntity(ticketDTO.getExecutor()));
        return ticket;
    }
}
