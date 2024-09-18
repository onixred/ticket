package my.pet.ticket.server.adapter.persistence.repository;

import my.pet.ticket.server.adapter.persistence.entity.TicketStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketStatusRepository extends JpaRepository<TicketStatusEntity, Long>, JpaSpecificationExecutor<TicketStatusEntity> {
}
