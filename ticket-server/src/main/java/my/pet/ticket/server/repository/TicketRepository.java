package my.pet.ticket.server.repository;

import my.pet.ticket.server.dao.TicketDao;
import my.pet.ticket.server.dao.TicketIdDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends CrudRepository<TicketDao, TicketIdDao> {
}
