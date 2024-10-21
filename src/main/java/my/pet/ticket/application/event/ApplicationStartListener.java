package my.pet.ticket.application.event;

import my.pet.ticket.logging.EventLog;
import my.pet.ticket.logging.EventType;
import my.pet.ticket.logging.Log;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Log.INFO("Handling application ready event", new EventLog(EventType.APP_START));
    }

}
