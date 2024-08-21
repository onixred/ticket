package my.pet.ticket.acl;

import my.pet.ticket.model.dto.TicketForCreateDto;
import my.pet.ticket.model.dto.TicketResponseDto;
import my.pet.ticket.model.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * TicketMapper интерфейс для преобразования сущности Ticket в dto
 *
 * @author <a href="mailto:baranov.alexalex@gmail.com">abaranov</a>
 */
@Mapper(componentModel = "spring")
public interface TicketMapper {

    TicketResponseDto entityToDto(Ticket ticket);

    @Mapping(target = "id", ignore = true)
    Ticket dtoToEntity(TicketForCreateDto ticketForCreateDto);
}
