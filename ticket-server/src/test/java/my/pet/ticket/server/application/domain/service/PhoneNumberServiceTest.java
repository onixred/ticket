package my.pet.ticket.server.application.domain.service;

import java.util.List;
import my.pet.ticket.server.ApplicationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class PhoneNumberServiceTest extends ApplicationTest {

  @Autowired
  PhoneNumberService phoneNumberService;

  @Test
  void getPhoneNumberTest() {
    Assertions.assertDoesNotThrow(() -> {
      this.phoneNumberService.get(1001L);
    });
  }

  @Test
  void getAllPhoneNumbersTest() {
    List<String> phoneNumbers = this.phoneNumberService.getAll();
    Assertions.assertFalse(phoneNumbers.isEmpty());
  }

  @Test
  void getAllPhoneNumbersPageableTest() {
    List<String> phoneNumbers1 = this.phoneNumberService.getAll(0, 1);
    List<String> phoneNumbers2 = this.phoneNumberService.getAll(1, 1);
    Assertions.assertNotEquals(phoneNumbers1.get(0), phoneNumbers2.get(0));
  }

  @Test
  void deletePhoneNumberTest() {
    Assertions.assertDoesNotThrow(() -> {
      this.phoneNumberService.delete(1004L);
    });
  }

}