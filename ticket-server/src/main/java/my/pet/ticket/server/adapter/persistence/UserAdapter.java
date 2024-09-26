package my.pet.ticket.server.adapter.persistence;

import java.util.List;
import java.util.Optional;
import my.pet.ticket.server.adapter.persistence.entity.UserEntity;
import my.pet.ticket.server.adapter.persistence.entity.UserEntity_;
import my.pet.ticket.server.adapter.persistence.repository.UserRepository;
import my.pet.ticket.server.application.port.persistence.UserPort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class UserAdapter implements UserPort {

  private static final Specification<UserEntity> NOT_DELETED_SPECIFICATION = ((root, query, criteriaBuilder) -> criteriaBuilder.equal(
      root.get(UserEntity_.DELETED), false));

  private final UserRepository userRepository;

  public UserAdapter(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Optional<UserEntity> get(Specification<UserEntity> specification) {
    return this.userRepository.findOne(NOT_DELETED_SPECIFICATION.and(specification));
  }

  @Override
  public List<UserEntity> getAll(Specification<UserEntity> specification, Pageable pageable) {
    return this.userRepository.findAll(NOT_DELETED_SPECIFICATION.and(specification), pageable)
        .stream().toList();
  }

  @Override
  public UserEntity create(UserEntity entity) {
    if (entity.getUserId() == null) {
      return this.userRepository.save(entity);
    }
    throw new PersistenceAdapterException("User shouldn't have id when creating");
  }

  @Override
  public UserEntity update(UserEntity entity) {
    if (this.userRepository.existsById(entity.getUserId())) {
      return this.userRepository.save(entity);
    }
    throw new PersistenceAdapterException("User not exist");
  }
}
