package my.pet.ticket.server.repository;

import my.pet.ticket.server.dao.PhoneNumberDao;
import my.pet.ticket.server.dao.PhoneNumberIdDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepository extends CrudRepository<PhoneNumberDao, PhoneNumberIdDao> {
}
