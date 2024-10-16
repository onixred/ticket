package my.pet.ticket.adapter.persistence;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import my.pet.ticket.domain.Ticket;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TicketEntityMapper {

    @Mapping(target = "id", ignore = true)
    TicketEntity toTicketEntity(Ticket ticket);

    Ticket toTicket(TicketEntity ticketEntity);

}
