package my.pet.ticket.kafka.consumer;

import my.pet.ticket.logging.EventLog;
import my.pet.ticket.logging.Log;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * KafkaListener class for
 *
 * @author <a href="mailto:baranov.alexalex@gmail.com">abaranov</a>
 */
@Component
public class KafkaListeners {

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.consumer.group-id}")
    void listener(String data) {
        Log.INFO("Kafka received message: " + data, EventLog.K_GET);
    }
}
