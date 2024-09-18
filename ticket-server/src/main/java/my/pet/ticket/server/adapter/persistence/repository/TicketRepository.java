package my.pet.ticket.server.adapter.persistence.repository;

import my.pet.ticket.server.adapter.persistence.entity.TicketEntity;
import my.pet.ticket.server.adapter.persistence.entity.TicketIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, TicketIdEntity>, JpaSpecificationExecutor<TicketEntity> {
}
