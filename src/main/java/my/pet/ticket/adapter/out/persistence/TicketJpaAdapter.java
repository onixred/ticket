package my.pet.ticket.adapter.out.persistence;

import org.springframework.data.repository.CrudRepository;

import my.pet.ticket.adapter.out.persistence.entity.TicketEntity;

import java.util.List;
import java.util.Optional;

public interface TicketJpaAdapter extends CrudRepository<TicketEntity, Long> {

    Optional<TicketEntity> findById(Long id);

    List<TicketEntity> findAll();

}
