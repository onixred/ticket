package my.pet.ticket.server.logging;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LogTest {

    @Test
    void testMessageDetail() {
        Log.WARN("Test message", new DefaultDetail("100", "Description"));
    }

    @Test
    void testMessageEventDetail() {
        Log.WARN("Test message", Events.BP_START.getEvent(), new DefaultDetail("100", "Description"));
    }

    @Test
    void testMessageEventCaseDetail() {
        Log.WARN("Test message", Events.BP_START.getEvent(), new NullPointerException("Test cause message"), new DefaultDetail("100", "Description"));
    }

}
