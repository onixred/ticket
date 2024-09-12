package my.pet.ticket.kafka.consumer;

import my.pet.ticket.kafka.model.TicketInfo;
import my.pet.ticket.logging.EventLog;
import my.pet.ticket.logging.Log;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

/**
 * KafkaListener class for
 *
 * @author <a href="mailto:baranov.alexalex@gmail.com">abaranov</a>
 */
@Component
public class KafkaListeners implements MessageListener<String, TicketInfo> {

    @Override
    public void onMessage(ConsumerRecord<String, TicketInfo> data) {
        Log.INFO("Kafka received message: " + data.value().getText() + " " + data.value().getEventLog(),
                EventLog.K_GET);
    }
}
