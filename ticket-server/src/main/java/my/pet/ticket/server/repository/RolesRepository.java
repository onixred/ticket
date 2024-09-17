package my.pet.ticket.server.repository;

import my.pet.ticket.server.dao.RoleDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends CrudRepository<RoleDao, Long> {
}
