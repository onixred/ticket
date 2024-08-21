package my.pet.ticket.service;

import lombok.RequiredArgsConstructor;
import my.pet.ticket.acl.TicketMapper;
import my.pet.ticket.dao.TicketRepository;
import my.pet.ticket.exception.TicketNotFoundException;
import my.pet.ticket.logging.EventLog;
import my.pet.ticket.logging.Log;
import my.pet.ticket.model.dto.TicketForCreateDto;
import my.pet.ticket.model.dto.TicketResponseDto;
import my.pet.ticket.model.entity.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    @Transactional(readOnly = true)
    @Override
    public List<TicketResponseDto> findAll() {
        Log.INFO("Метод service.findAll()", EventLog.T_FIND);

        List<Ticket> listTickets = ticketRepository.findAll();

        return listTickets.stream()
                .map(ticketMapper::entityToDto)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public TicketResponseDto findById(Long id) {
        Log.INFO("Метод service.findById()", EventLog.T_FIND);

        return ticketMapper.entityToDto(ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Задача  c id " + id  + " не найдена")));
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public TicketResponseDto create(TicketForCreateDto ticketForCreateDto) {
        Log.INFO("Метод service.create()", EventLog.T_CREATE);

        Ticket ticket = ticketRepository.save(ticketMapper.dtoToEntity(ticketForCreateDto));

        return ticketMapper.entityToDto(ticket);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public Boolean delete(Long id) {
        Log.INFO("Метод service.delete()", EventLog.T_FIND);

        ticketRepository.delete(ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Задача  c id " + id  + " не найдена")));

        return true;
    }
}