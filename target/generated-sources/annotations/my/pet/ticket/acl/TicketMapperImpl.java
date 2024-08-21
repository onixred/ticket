package my.pet.ticket.acl;

import javax.annotation.processing.Generated;
import my.pet.ticket.model.dto.TicketForCreateDto;
import my.pet.ticket.model.dto.TicketResponseDto;
import my.pet.ticket.model.entity.Ticket;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-21T08:09:44+0700",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class TicketMapperImpl implements TicketMapper {

    @Override
    public TicketResponseDto entityToDto(Ticket ticket) {
        if ( ticket == null ) {
            return null;
        }

        TicketResponseDto ticketResponseDto = new TicketResponseDto();

        return ticketResponseDto;
    }

    @Override
    public Ticket dtoToEntity(TicketForCreateDto ticketForCreateDto) {
        if ( ticketForCreateDto == null ) {
            return null;
        }

        Ticket ticket = new Ticket();

        return ticket;
    }
}
