package my.pet.ticket.server.repository;

import my.pet.ticket.server.dao.TicketStatusDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketStatusRepository extends CrudRepository<TicketStatusDao, Long> {
}
