package my.pet.ticket.service;

import my.pet.ticket.model.dto.TicketForCreateDto;
import my.pet.ticket.model.dto.TicketResponseDto;

import java.util.List;

/**
 * TicketService сервис для работы с бизнес логикой и доступом к репозиторию
 *
 * @author <a href="mailto:baranov.alexalex@gmail.com">abaranov</a>
 */
public interface TicketService {

    List<TicketResponseDto> findAll();

    TicketResponseDto findById(Long id);

    TicketResponseDto create(TicketForCreateDto ticketForCreateDto);

    Boolean delete(Long id);
}
