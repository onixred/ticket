package my.pet.ticket.server.adapter.persistence;

import java.util.Optional;
import my.pet.ticket.server.adapter.persistence.entity.RoleEntity;
import my.pet.ticket.server.adapter.persistence.entity.RoleEntity_;
import my.pet.ticket.server.adapter.persistence.repository.RoleRepository;
import my.pet.ticket.server.application.port.persistence.RolePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class RoleAdapter implements RolePort {

  private static final Specification<RoleEntity> NOT_DELETED_SPECIFICATION = (root, query, criteriaBuilder) -> criteriaBuilder.equal(
      root.get(RoleEntity_.DELETED), false);

  private final RoleRepository roleRepository;

  public RoleAdapter(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Override
  public Optional<RoleEntity> get(Specification<RoleEntity> specification) {
    return this.roleRepository.findOne(NOT_DELETED_SPECIFICATION.and(specification));
  }

  @Override
  public Page<RoleEntity> getAll(Specification<RoleEntity> specification, Pageable pageable) {
    return this.roleRepository.findAll(NOT_DELETED_SPECIFICATION.and(specification),
        pageable);
  }

  @Override
  public RoleEntity create(RoleEntity entity) {
    if (entity.getRoleId() == null) {
      return this.roleRepository.save(entity);
    }
    throw new PersistenceAdapterException("Role shouldn't have id when creating");
  }

  @Override
  public RoleEntity update(RoleEntity entity) {
    if (this.roleRepository.existsById(entity.getRoleId())) {
      return this.roleRepository.save(entity);
    }
    throw new PersistenceAdapterException("Role not exist");
  }

}
