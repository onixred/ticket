package my.pet.ticket.service;

import lombok.RequiredArgsConstructor;
import my.pet.ticket.acl.TicketMapper;
import my.pet.ticket.dao.TicketRepository;
import my.pet.ticket.exception.TicketNotFoundException;
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
        List<Ticket> listTickets = ticketRepository.findAll();

        return listTickets.stream()
                .map(ticketMapper::entityToDto)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public TicketResponseDto findById(Long id) {

        return ticketMapper.entityToDto(ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Задача  c id " + id  + " не найдена")));
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public TicketResponseDto create(TicketForCreateDto ticketForCreateDto) {
        Ticket ticket = ticketRepository.save(ticketMapper.dtoToEntity(ticketForCreateDto));

        return ticketMapper.entityToDto(ticket);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public Boolean delete(Long id) {
        ticketRepository.delete(ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Задача  c id " + id  + " не найдена")));

        return true;
    }
}