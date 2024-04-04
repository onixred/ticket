package my.pet.ticket.controller;

import my.pet.ticket.config.dto.AppProperties;
import my.pet.ticket.entity.Ticket;
import my.pet.ticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController("/test")
public class TestController {

    @Autowired
    AppProperties appProperties;

    @Autowired
    TicketRepository ticketRepository;

    @GetMapping("/user")
    public void test() throws SQLException {

        Ticket ticket = new Ticket();
        ticket.setName("1111111");
        ticketRepository.save(ticket);

        System.out.println(ticketRepository.findAll());
        System.out.println(appProperties.getTestUrl());
    }
}
