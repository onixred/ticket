package my.pet.ticket.server.application.port.persistence;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;
import java.util.List;
import my.pet.ticket.server.adapter.persistence.PersistenceAdapterException;
import my.pet.ticket.server.adapter.persistence.entity.PhoneNumberEntity;
import my.pet.ticket.server.adapter.persistence.entity.PhoneNumberEntity_;
import my.pet.ticket.server.adapter.persistence.entity.PhoneNumberIdEntity_;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
class PhoneNumberPortTest {

  @Autowired
  PhoneNumberPort phoneNumberPort;

  @Test
  void createTest() {
    PhoneNumberEntity phoneNumberEntity = PhoneNumberEntity.builder()
        .clientId(1004L)
        .nationalPrefix(8)
        .regionCode(888)
        .number(4444444)
        .fullNumber("88884444444")
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .deleted(false)
        .build();
    PhoneNumberEntity phoneNumber = this.phoneNumberPort.create(phoneNumberEntity);
    Assertions.assertNotNull(phoneNumber.getId().getPhoneNumberId());
  }

  @Test
  void getTestNotThrow() {
    Assertions.assertDoesNotThrow(() -> {
      this.phoneNumberPort.get((
          (root, query, criteriaBuilder) -> criteriaBuilder.equal(
              root.get(PhoneNumberEntity_.ID)
                  .get(PhoneNumberIdEntity_.PHONE_NUMBER_ID),
              1001
          )
      )).orElseThrow(() -> new PersistenceAdapterException("Phone number not found"));
    });
  }

  @Test
  void getTestThrow() {
    Assertions.assertThrows(PersistenceAdapterException.class, () -> {
      this.phoneNumberPort.get((
          (root, query, criteriaBuilder) -> criteriaBuilder.equal(
              root.get(PhoneNumberEntity_.ID)
                  .get(PhoneNumberIdEntity_.PHONE_NUMBER_ID),
              999
          )
      )).orElseThrow(() -> new PersistenceAdapterException("Phone number not found"));
    });
  }

  @Test
  void getAllTest() {
    List<PhoneNumberEntity> phoneNumberEntities = this.phoneNumberPort.getAll(
        ((root, query, criteriaBuilder) -> criteriaBuilder.conjunction()));
    assertFalse(phoneNumberEntities.isEmpty());
  }

  @Test
  void updateNotThrow() {
    PhoneNumberEntity phoneNumber = this.phoneNumberPort.get(
        (root, query, criteriaBuilder) -> criteriaBuilder.equal(
            root.get(PhoneNumberEntity_.ID).get(PhoneNumberIdEntity_.PHONE_NUMBER_ID),
            1001
        )).orElseThrow(() -> new PersistenceAdapterException("Phone number not found"));
    phoneNumber.setNationalPrefix(7);
    phoneNumber.setFullNumber(String.valueOf(phoneNumber.getNationalPrefix()) +
        phoneNumber.getRegionCode() +
        phoneNumber.getNumber());
    PhoneNumberEntity updatedPhoneNumber = this.phoneNumberPort.update(phoneNumber);
    Assertions.assertEquals(7, updatedPhoneNumber.getNationalPrefix());
  }

  @Test
  void updateThrow() {
    PhoneNumberEntity phoneNumber = this.phoneNumberPort.get(
        (root, query, criteriaBuilder) -> criteriaBuilder.equal(
            root.get(PhoneNumberEntity_.ID).get(PhoneNumberIdEntity_.PHONE_NUMBER_ID),
            1001
        )).orElseThrow(() -> new PersistenceAdapterException("Phone number not found"));
    phoneNumber.getId().setPhoneNumberId(999L);
    Assertions.assertThrows(PersistenceAdapterException.class, () -> {
      this.phoneNumberPort.update(phoneNumber);
    });
  }

  @Test
  void deleteNotThrow() {
    Assertions.assertDoesNotThrow(() -> {
      PhoneNumberEntity phoneNumber = this.phoneNumberPort.get(
          (root, query, criteriaBuilder) -> criteriaBuilder.equal(
              root.get(PhoneNumberEntity_.ID).get(PhoneNumberIdEntity_.PHONE_NUMBER_ID),
              1002
          )).orElseThrow(() -> new PersistenceAdapterException("Phone number not found"));
      this.phoneNumberPort.delete(phoneNumber);
    });
  }

  @Test
  void deleteThrow() {
    Assertions.assertThrows(PersistenceAdapterException.class, () -> {
      this.phoneNumberPort.get(
          (root, query, criteriaBuilder) -> criteriaBuilder.equal(
              root.get(PhoneNumberEntity_.ID).get(PhoneNumberIdEntity_.PHONE_NUMBER_ID),
              1003
          )).orElseThrow(() -> new PersistenceAdapterException("Phone number not found"));
    });
  }

}