package my.pet.ticket.infrastructure.event;

import my.pet.ticket.infrastructure.logging.EventType;
import my.pet.ticket.infrastructure.logging.Log;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Log.INFO("Обработка события запуска приложения", EventType.APP_START);
    }

}
