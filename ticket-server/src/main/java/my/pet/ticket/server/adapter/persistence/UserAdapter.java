package my.pet.ticket.server.adapter.persistence;

import my.pet.ticket.server.adapter.persistence.entity.UserEntity;
import my.pet.ticket.server.adapter.persistence.repository.UserRepository;
import my.pet.ticket.server.application.port.persistence.UserPort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserAdapter implements UserPort {

    private final UserRepository userRepository;

    public UserAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserEntity> get(Specification<UserEntity> specification) {
        return this.userRepository.findOne(specification);
    }

    @Override
    public List<UserEntity> getAll(Specification<UserEntity> specification, Pageable pageable) {
        return this.userRepository.findAll(specification, pageable).stream().toList();
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
