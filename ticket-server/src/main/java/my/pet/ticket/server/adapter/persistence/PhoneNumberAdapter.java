package my.pet.ticket.server.adapter.persistence;

import my.pet.ticket.server.adapter.persistence.entity.PhoneNumberEntity;
import my.pet.ticket.server.adapter.persistence.repository.PhoneNumberRepository;
import my.pet.ticket.server.application.port.persistence.PhoneNumberPort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PhoneNumberAdapter implements PhoneNumberPort {

    private final PhoneNumberRepository phoneNumberRepository;

    public PhoneNumberAdapter(PhoneNumberRepository phoneNumberRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
    }

    @Override
    public Optional<PhoneNumberEntity> get(Specification<PhoneNumberEntity> specification) {
        return this.phoneNumberRepository.findOne(specification);
    }

    @Override
    public List<PhoneNumberEntity> getAll(Specification<PhoneNumberEntity> specification, Pageable pageable) {
        return this.phoneNumberRepository.findAll(specification, pageable).stream().toList();
    }

    @Override
    public PhoneNumberEntity create(PhoneNumberEntity entity) {
        if (entity.getId().getPhoneId() == null) {
            return this.phoneNumberRepository.save(entity);
        }
        throw new RuntimeException(); //TODO: Custom exception
    }

    @Override
    public PhoneNumberEntity update(PhoneNumberEntity entity) {
        if (this.phoneNumberRepository.existsById(entity.getId())) {
            return this.phoneNumberRepository.save(entity);
        }
        throw new RuntimeException(); //TODO: Custom exception
    }

    @Override
    public void delete(PhoneNumberEntity entity) {
        if (this.phoneNumberRepository.existsById(entity.getId())) {
            entity.setDeleted(true);
            this.phoneNumberRepository.save(entity);
            return;
        }
        throw new RuntimeException(); //TODO: Custom exception
    }
}
