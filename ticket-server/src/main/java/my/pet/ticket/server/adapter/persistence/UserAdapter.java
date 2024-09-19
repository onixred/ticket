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
        return this.userRepository.save(entity);
    }

    @Override
    public UserEntity update(UserEntity entity) {
        if (this.userRepository.existsById(entity.getUserId())) {
            this.userRepository.save(entity);
        }
        throw new RuntimeException(); //TODO: Custom exception
    }

    @Override
    public void delete(UserEntity entity) {
        entity.setDeleted(true);
        if (this.userRepository.existsById(entity.getUserId())) {
            this.userRepository.save(entity);
        } else {
            throw new RuntimeException(); //TODO: Custom exception
        }
    }
}
