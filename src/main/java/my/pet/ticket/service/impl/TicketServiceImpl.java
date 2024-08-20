package my.pet.ticket.service.impl;

import lombok.RequiredArgsConstructor;
import my.pet.ticket.acl.TicketMapper;
import my.pet.ticket.dao.TicketRepository;
import my.pet.ticket.exception.TicketNotFoundException;
import my.pet.ticket.model.dto.TicketForCreateDto;
import my.pet.ticket.model.dto.TicketResponseDto;
import my.pet.ticket.model.entity.Ticket;
import my.pet.ticket.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    @Override
    public List<TicketResponseDto> findAllTickets() {
        List<Ticket> listTickets = ticketRepository.findAll();

        return listTickets.stream()
                .map(ticketMapper::entityToDto)
                .toList();
    }

    @Override
    public TicketResponseDto findTicketById(Long id) {

        return ticketMapper.entityToDto(ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Задача не найдена")));
    }

    @Override
    public void createTicket(TicketForCreateDto ticketForCreateDto) {
        ticketRepository.save(ticketMapper.dtoToEntity(ticketForCreateDto));
    }

    @Override
    public void deleteTicket(Long id) {
        ticketRepository.delete(ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Задача не найдена")));
    }
}
