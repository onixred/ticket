package my.pet.ticket.server.application.port.configuration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConfigurationPortTest {

    @Autowired
    private ConfigurationPort configurationPort;

    @Test
    void getFirst () {
        TestValue actual = configurationPort.getFirst(TestValue.class, "test", null);
        Assertions.assertEquals(new TestValue("test"), actual);
    }

}