package my.pet.ticket.server.application.port.configuration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
class ConfigurationPortTest {

  @Autowired
  private ConfigurationPort configurationPort;

  @Test
  void getFirst() {
    String actual = configurationPort.getFirst("test.value");
    Assertions.assertEquals("value", actual);
  }

}