package my.pet.ticket.server.repository;

import my.pet.ticket.server.dao.UserDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserDao, Long> {
}
