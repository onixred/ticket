package my.pet.ticket.server.common.logging;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
public class LogTest {

    private static final String MESSAGE = "Test Message";

    private static final Event EVENT = DefaultEvent.BP_START;

    private static final Throwable CAUSE = new NullPointerException("Test cause message");

    private static final Detail[] DETAILS = new Detail[]{
            new DefaultDetail("100", "Test description"), new DefaultDetail("101")
    };

    @Test
    void testDebugMessageDetail() {
        Log.DEBUG(MESSAGE, DETAILS);
    }

    @Test
    void testDebugMessageEventDetail() {
        Log.DEBUG(MESSAGE, EVENT, DETAILS);
    }

    @Test
    void testInfoMessageDetail() {
        Log.INFO(MESSAGE, DETAILS);
    }

    @Test
    void testInfoMessageEventDetail() {
        Log.INFO(MESSAGE, EVENT, DETAILS);
    }

    @Test
    void testErrorMessageDetail() {
        Log.ERROR(MESSAGE, DETAILS);
    }

    @Test
    void testErrorMessageEventDetail() {
        Log.ERROR(MESSAGE, EVENT, DETAILS);
    }

    @Test
    void testErrorMessageEventCauseDetail() {
        Log.ERROR(MESSAGE, EVENT, CAUSE, DETAILS);
    }

    @Test
    void testWarnMessageDetail() {
        Log.WARN(MESSAGE, DETAILS);
    }

    @Test
    void testWarnMessageEventDetail() {
        Log.WARN(MESSAGE, EVENT, DETAILS);
    }

    @Test
    void testWarnMessageEventCauseDetail() {
        Log.WARN(MESSAGE, EVENT, CAUSE, DETAILS);
    }

    @Test
    void testMessageDetail() {
        String expected = "Message: Test Message | Details: [{\"errorCode\":\"100\",\"description\":\"Test description\"},{\"errorCode\":\"101\",\"description\":null}]";
        String actual = Log.writeLogToString(MESSAGE, DETAILS);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testMessageEventDetail() {
        String expected = "Message: Test Message | Event: {\"name\":\"Business process start\"} | Details: [{\"errorCode\":\"100\",\"description\":\"Test description\"},{\"errorCode\":\"101\",\"description\":null}]";
        String actual = Log.writeLogToString(MESSAGE, EVENT, DETAILS);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testMessageEventCaseDetail() {
        String expected = "Message: Test Message | Event: {\"name\":\"Business process start\"} | Cause: Test cause message | Details: [{\"errorCode\":\"100\",\"description\":\"Test description\"},{\"errorCode\":\"101\",\"description\":null}]";
        String actual = Log.writeLogToString(MESSAGE, EVENT, CAUSE, DETAILS);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testAllNull() {
        String expected = "Message: null | Event: null | Cause: null | Details: null";
        String actual = Log.writeLogToString(null, null, null, null);
        Assertions.assertEquals(expected, actual);
    }


}
