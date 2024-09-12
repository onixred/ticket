package my.pet.ticket.property;

import lombok.Data;

/**
 * KafkaProperty class for
 *
 * @author <a href="mailto:baranov.alexalex@gmail.com">abaranov</a>
 */
@Data
public class KafkaProperty {
    private String bootstrapServers;
    private String topicName;
    private String consumerGroupId;
}
