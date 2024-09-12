package my.pet.ticket.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TicketInfo class for
 *
 * @author <a href="mailto:baranov.alexalex@gmail.com">abaranov</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketInfo {
    private String text;
    private String eventLog;
}
