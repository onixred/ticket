package my.pet.ticket.mock;

import java.util.Arrays;
import java.util.List;
import my.pet.ticket.model.model.BalanceMock;
import org.springframework.stereotype.Service;

/**
 * TicketMock class for create mock
 *
 * @author <a href="mailto:baranov.alexalex@gmail.com">abaranov</a>
 */
@Service
public class TicketMockService {

    private final List<BalanceMock> balanceMockList = Arrays.asList(
            new BalanceMock(1L, 111),
            new BalanceMock(2L, 222),
            new BalanceMock(3L, 333),
            new BalanceMock(4L, 444)
    );

    public BalanceMock getBalanceMockById(Long id) {

        return balanceMockList.stream()
                .filter(balanceMock -> balanceMock.getTicketId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
