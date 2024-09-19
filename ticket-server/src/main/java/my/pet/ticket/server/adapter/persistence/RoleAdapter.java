package my.pet.ticket.server.adapter.persistence;

import my.pet.ticket.server.adapter.persistence.entity.RoleEntity;
import my.pet.ticket.server.adapter.persistence.repository.RoleRepository;
import my.pet.ticket.server.application.port.persistence.RolePort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RoleAdapter implements RolePort {

    private final RoleRepository roleRepository;

    public RoleAdapter(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<RoleEntity> get(Specification<RoleEntity> specification) {
        return this.roleRepository.findOne(specification);
    }

    @Override
    public List<RoleEntity> getAll(Specification<RoleEntity> specification, Pageable pageable) {
        return this.roleRepository.findAll(specification, pageable).stream().toList();
    }

    @Override
    public RoleEntity create(RoleEntity entity) {
        if (entity.getRoleId() == null) {
            return this.roleRepository.save(entity);
        }
        throw new RuntimeException(); //TODO: Custom exception
    }

    @Override
    public RoleEntity update(RoleEntity entity) {
        if (this.roleRepository.existsById(entity.getRoleId())) {
            return this.roleRepository.save(entity);
        }
        throw new RuntimeException(); //TODO: Custom exception
    }

    @Override
    public void delete(RoleEntity entity) {
        if (this.roleRepository.existsById(entity.getRoleId())) {
            entity.setDeleted(true);
            this.roleRepository.save(entity);
            return;
        }
        throw new RuntimeException(); //TODO: Custom exception;
    }
}
