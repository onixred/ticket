package my.pet.ticket.server.repository;

import my.pet.ticket.server.dao.ClientDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<ClientDao, Long> {
}
