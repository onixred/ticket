package my.pet.ticket.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.pet.ticket.config.dto.AppProperties;
import my.pet.ticket.entity.Ticket;
import my.pet.ticket.repository.TicketRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController("/test")
@AllArgsConstructor
@Slf4j
public class TestController {

    AppProperties appProperties;

    TicketRepository ticketRepository;

    @GetMapping("/user")
    public void test() throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setName("1111111");
        ticketRepository.save(ticket);

        log.info("{}", ticketRepository.findAll());
        log.info("{}", appProperties.getTestUrl());
    }
}
