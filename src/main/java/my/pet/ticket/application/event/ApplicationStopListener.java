package my.pet.ticket.application.event;

import my.pet.ticket.logging.EventLog;
import my.pet.ticket.logging.EventType;
import my.pet.ticket.logging.Log;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStopListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        Log.INFO("Handling application stopped event", new EventLog(EventType.APP_STOP));
    }

}
