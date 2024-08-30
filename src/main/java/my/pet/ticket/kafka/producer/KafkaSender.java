package my.pet.ticket.kafka.producer;

import lombok.RequiredArgsConstructor;
import my.pet.ticket.logging.EventLog;
import my.pet.ticket.logging.Log;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * KafkaSender class for
 *
 * @author <a href="mailto:baranov.alexalex@gmail.com">abaranov</a>
 */
@Component
@RequiredArgsConstructor
public class KafkaSender {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message, String topicName) {
        Log.INFO("Kafka sending message: " + message, EventLog.K_SEND);

        kafkaTemplate.send(topicName, message);
    }
}
