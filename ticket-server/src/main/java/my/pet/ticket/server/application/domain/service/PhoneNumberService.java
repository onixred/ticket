package my.pet.ticket.server.application.domain.service;

import java.util.List;
import my.pet.ticket.server.adapter.persistence.entity.PhoneNumberEntity;
import my.pet.ticket.server.adapter.persistence.entity.PhoneNumberEntity_;
import my.pet.ticket.server.adapter.persistence.entity.PhoneNumberIdEntity_;
import my.pet.ticket.server.application.port.persistence.PhoneNumberPort;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PhoneNumberService implements DomainService<String, PhoneNumberEntity> {

  private static final DomainServiceException PHONE_NUMBER_NOT_FOUND = new DomainServiceException(
      "Phone number not found");

  private final PhoneNumberPort phoneNumberPort;

  public PhoneNumberService(PhoneNumberPort phoneNumberPort) {
    this.phoneNumberPort = phoneNumberPort;
  }

  @Override
  @Transactional
  public String get(Long id) {
    PhoneNumberEntity phoneNumberEntity = this.phoneNumberPort.get(
            ((root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(PhoneNumberEntity_.ID).get(PhoneNumberIdEntity_.PHONE_NUMBER_ID), id)))
        .orElseThrow(() -> PHONE_NUMBER_NOT_FOUND);
    return convertEntityToModel(phoneNumberEntity);
  }

  @Override
  @Transactional
  public List<String> getAll() {
    return DomainService.super.getAll();
  }

  @Override
  @Transactional
  public List<String> getAll(Integer page, Integer pageSize) {
    return DomainService.super.getAll(page, pageSize);
  }

  @Transactional
  public String getByClientId(Long id) {
    PhoneNumberEntity phoneNumberEntity = this.phoneNumberPort.get(
            ((root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(PhoneNumberEntity_.ID).get(PhoneNumberIdEntity_.CLIENT_ID), id)))
        .orElseThrow(() -> PHONE_NUMBER_NOT_FOUND);
    return convertEntityToModel(phoneNumberEntity);
  }

  @Override
  @Transactional
  public void delete(Long id) {
    PhoneNumberEntity phoneNumberEntity = this.phoneNumberPort.get(
            ((root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(PhoneNumberEntity_.ID).get(PhoneNumberIdEntity_.PHONE_NUMBER_ID), id)))
        .orElseThrow(() -> PHONE_NUMBER_NOT_FOUND);
    this.phoneNumberPort.delete(phoneNumberEntity);
  }

  @Transactional
  public void deleteByClientId(Long id) {
    PhoneNumberEntity phoneNumberEntity = this.phoneNumberPort.get(
            ((root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(PhoneNumberEntity_.ID).get(PhoneNumberIdEntity_.CLIENT_ID), id)))
        .orElseThrow(() -> PHONE_NUMBER_NOT_FOUND);
    this.phoneNumberPort.delete(phoneNumberEntity);
  }

  @Override
  @Transactional
  public List<String> getAll(Pageable pageable) {
    return this.phoneNumberPort.getAll(
            ((root, query, criteriaBuilder) -> criteriaBuilder.conjunction()), pageable)
        .stream()
        .map(this::convertEntityToModel)
        .toList();
  }

  @Override
  public String convertEntityToModel(PhoneNumberEntity entity) {
    return entity.getFullNumber();
  }

}
