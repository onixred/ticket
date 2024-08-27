package my.pet.ticket.model.model;

import lombok.Data;

/**
 * BalanceMock  класс для создания модели мока
 *
 * @author <a href="mailto:baranov.alexalex@gmail.com">abaranov</a>
 */
@Data
public class BalanceMock {
    private Long ticketId;
    private Integer balance;

    public BalanceMock(Long ticketId, Integer balance) {
        this.ticketId = ticketId;
        this.balance = balance;
    }
}
