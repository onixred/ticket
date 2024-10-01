package my.pet.ticket.server.adapter.persistence;

import java.util.Optional;
import my.pet.ticket.server.adapter.persistence.entity.PhoneNumberEntity;
import my.pet.ticket.server.adapter.persistence.entity.PhoneNumberEntity_;
import my.pet.ticket.server.adapter.persistence.repository.PhoneNumberRepository;
import my.pet.ticket.server.application.port.persistence.PhoneNumberPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PhoneNumberAdapter implements PhoneNumberPort {

  private final static Specification<PhoneNumberEntity> NOT_DELETED_SPECIFICATION = ((root, query, criteriaBuilder) -> criteriaBuilder.equal(
      root.get(PhoneNumberEntity_.DELETED), false));

  private final PhoneNumberRepository phoneNumberRepository;

  public PhoneNumberAdapter(PhoneNumberRepository phoneNumberRepository) {
    this.phoneNumberRepository = phoneNumberRepository;
  }

  @Override
  public Optional<PhoneNumberEntity> get(Specification<PhoneNumberEntity> specification) {
    return this.phoneNumberRepository.findOne(NOT_DELETED_SPECIFICATION.and(specification));
  }

  @Override
  public Page<PhoneNumberEntity> getAll(Specification<PhoneNumberEntity> specification,
      Pageable pageable) {
    return this.phoneNumberRepository.findAll(NOT_DELETED_SPECIFICATION.and(specification),
        pageable);
  }

  @Override
  public PhoneNumberEntity create(PhoneNumberEntity entity) {
    if (entity.getId().getPhoneNumberId() == null) {
      return this.phoneNumberRepository.save(entity);
    }
    throw new PersistenceAdapterException("Phone number shouldn't have id when creating");
  }

  @Override
  public PhoneNumberEntity update(PhoneNumberEntity entity) {
    if (this.phoneNumberRepository.existsById(entity.getId())) {
      return this.phoneNumberRepository.save(entity);
    }
    throw new PersistenceAdapterException("Phone number not exist");
  }

}
