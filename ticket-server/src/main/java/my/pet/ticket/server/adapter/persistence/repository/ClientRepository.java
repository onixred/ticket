package my.pet.ticket.server.adapter.persistence.repository;

import my.pet.ticket.server.adapter.persistence.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository
        extends JpaRepository<ClientEntity, Long>, JpaSpecificationExecutor<ClientEntity> {

}
