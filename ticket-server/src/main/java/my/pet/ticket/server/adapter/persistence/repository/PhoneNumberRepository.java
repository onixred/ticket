package my.pet.ticket.server.adapter.persistence.repository;

import my.pet.ticket.server.adapter.persistence.entity.PhoneNumberEntity;
import my.pet.ticket.server.adapter.persistence.entity.PhoneNumberIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumberEntity, PhoneNumberIdEntity>, JpaSpecificationExecutor<PhoneNumberEntity> {
}
