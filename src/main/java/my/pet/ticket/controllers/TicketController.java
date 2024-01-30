package my.pet.ticket.controllers;

import my.pet.ticket.dataLayer.dao.interfaces.TicketRepository;
import my.pet.ticket.dataLayer.model.Ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketRepository ticketRepository;

    // Метод для получения всех задач с пагинацией и фильтрацией
    @GetMapping
    public Page<Ticket> getAllTickets(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String taskName
    ) {
        Specification<Ticket> spec = Specification.where(null);

        if (status != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("status"), status));
        }

        if (taskName != null) {
            spec = spec.and((root, query, cb) -> cb.like(root.get("taskName"), "%" + taskName + "%"));
        }

        return ticketRepository.findAll(spec, PageRequest.of(page, size));
    }

    // Метод для создания новой задачи
    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {
        // Валидация и сохранение задачи
        return ticketRepository.save(ticket);
    }
}
