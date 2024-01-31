package my.pet.ticket.dataLayer.dao.interfaces;

import my.pet.ticket.dataLayer.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
