package my.pet.ticket.logging;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EventLog implements Event {

    private final EventType eventType;

    @Override
    public String getErrorCode() {
        return eventType.getErrorCode();
    }

    @Override
    public String getDescription() {
        return eventType.getDescription();
    }

}
