package my.pet.ticket.application.event;

import my.pet.ticket.logging.EventLog;
import my.pet.ticket.logging.EventType;
import my.pet.ticket.logging.Log;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationFailedListener implements ApplicationListener<ApplicationFailedEvent> {

    @Override
    public void onApplicationEvent(ApplicationFailedEvent event) {
        Log.ERROR("Handling application start failure event", new EventLog(EventType.APP_INIT));
    }

}
