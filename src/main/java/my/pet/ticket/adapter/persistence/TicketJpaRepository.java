package my.pet.ticket.adapter.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TicketJpaRepository extends CrudRepository<TicketEntity, Long> {

    Optional<TicketEntity> findById(Long id);

    List<TicketEntity> findAll();

}
