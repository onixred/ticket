package my.pet.ticket.infrastructure.event;

import my.pet.ticket.infrastructure.logging.EventType;
import my.pet.ticket.infrastructure.logging.Log;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStopListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        Log.INFO("Обработка события остановки приложения", EventType.APP_STOP);
    }

}
