package my.pet.ticket.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import my.pet.ticket.model.dto.TicketForCreateDto;
import my.pet.ticket.model.dto.TicketResponseDto;
import my.pet.ticket.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Ticket APIs")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/ticket")
public class TicketController {

    private final TicketService ticketService;

    @Operation(summary = "Получение всех имеющихся задач")
    @GetMapping
    public ResponseEntity<List<TicketResponseDto>> findAll() {
        return new ResponseEntity<>(ticketService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Получение задачи по id")
    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDto> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(ticketService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Добавление новой задачи")
    @PostMapping
    public ResponseEntity<TicketResponseDto> create(@Valid @RequestBody TicketForCreateDto ticketForCreateDto) {
        return new ResponseEntity<>(ticketService.create(ticketForCreateDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Удаление задачи по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(ticketService.delete(id), HttpStatus.OK);
    }
}
