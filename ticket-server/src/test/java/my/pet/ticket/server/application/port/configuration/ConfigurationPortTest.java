package my.pet.ticket.server.application.port.configuration;

import my.pet.ticket.server.ApplicationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class ConfigurationPortTest extends ApplicationTest {

  @Autowired
  private ConfigurationPort configurationPort;

  @Test
  void getFirst() {
    String actual = configurationPort.getFirst("test.value");
    Assertions.assertEquals("value", actual);
  }

}