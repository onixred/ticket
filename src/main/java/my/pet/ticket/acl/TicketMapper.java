package my.pet.ticket.acl;

import my.pet.ticket.model.dto.TicketForCreateDto;
import my.pet.ticket.model.dto.TicketResponseDto;
import my.pet.ticket.model.entity.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    public TicketResponseDto entityToDto(Ticket ticket) {

        return TicketResponseDto.builder()
                .id(ticket.getId())
                .name(ticket.getName())
                .description(ticket.getDescription())
                .build();
    }

    public Ticket dtoToEntity(TicketForCreateDto ticketForCreateDto) {

        return Ticket.builder()
                .name(ticketForCreateDto.getName())
                .description(ticketForCreateDto.getDescription())
                .build();
    }
}
