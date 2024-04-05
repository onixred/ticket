package my.pet.ticket.repository;

import my.pet.ticket.entity.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    List<Ticket> findAllByIdIn(List<Long> longs);
}
