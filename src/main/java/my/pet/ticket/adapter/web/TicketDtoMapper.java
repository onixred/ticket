package my.pet.ticket.adapter.web;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import my.pet.ticket.domain.Ticket;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TicketDtoMapper {

    TicketDto toTicketDto(Ticket ticket);

    Ticket toTicket(TicketDto ticketDto);

}
