package my.pet.ticket.kafka.producer;

import lombok.RequiredArgsConstructor;
import my.pet.ticket.kafka.model.TicketInfo;
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

    private final KafkaTemplate<String, TicketInfo> kafkaTemplate;

    public void sendMessage(String topicName, TicketInfo ticketInfo) {
        Log.INFO("Kafka sending message: " + ticketInfo.getText() + ", " + ticketInfo.getEventLog(),
                EventLog.K_SEND);

        kafkaTemplate.send(topicName, ticketInfo);
    }
}
