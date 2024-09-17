package my.pet.ticket.server.logging;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

public class LogTest {

    @Test
    void testMessageDetail() {
        String expected = "Message: Test message | Details: [{\"errorCode\":\"100\",\"description\":\"Description\"}]";
        String actual = Log.writeLogToString(
                "Test message",
                new DefaultDetail("100", "Description")
        );
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testMessageDetailNoDescription() {
        String expected = "Message: Test message | Details: [{\"errorCode\":\"100\",\"description\":null}]";
        String actual = Log.writeLogToString(
                "Test message",
                new DefaultDetail("100")
        );
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testMessageEventDetail() {
        String expected = "Message: Test message | Event: {\"name\":\"Business process start\"} | Details: [{\"errorCode\":\"100\",\"description\":\"Description\"}]";
        String actual = Log.writeLogToString(
                "Test message",
                Events.BP_START.getEvent(),
                new DefaultDetail("100", "Description")
        );
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testMessageEventCaseDetail() {
        String expected = "Message: Test message | Event: {\"name\":\"Business process start\"} | Cause: Test cause message | Details: [{\"errorCode\":\"100\",\"description\":\"Description\"}]";
        String actual = Log.writeLogToString(
                "Test message",
                Events.BP_START.getEvent(),
                new NullPointerException("Test cause message"),
                new DefaultDetail("100", "Description")
        );
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testAllNull() {
        String expected = "Message: null | Event: null | Cause: null | Details: null";
        String actual = Log.writeLogToString(
                null,
                null,
                null,
                null
        );
        Assertions.assertEquals(expected, actual);
    }


}
