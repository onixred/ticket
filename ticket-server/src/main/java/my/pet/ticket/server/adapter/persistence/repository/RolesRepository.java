package my.pet.ticket.server.adapter.persistence.repository;

import my.pet.ticket.server.adapter.persistence.entity.RoleEntity;
import my.pet.ticket.server.application.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<RoleEntity, Long>, JpaSpecificationExecutor<RoleEntity> {
}
