package my.pet.ticket.grpc.client;

import my.pet.ticket.grpc.TicketRequest;
import my.pet.ticket.grpc.TicketResponse;
import my.pet.ticket.grpc.TicketServiceGrpc;
import org.springframework.stereotype.Service;

/**
 * TicketClientGrpc используется как клиент grpc
 *
 * @author <a href="mailto:baranov.alexalex@gmail.com">abaranov</a>
 */
@Service
public class TicketClientGrpc {

    private TicketServiceGrpc.TicketServiceBlockingStub ticketServiceStub;

    public Integer getBalance(Long ticketId) {
        TicketRequest ticketRequest = TicketRequest.newBuilder().setTicketId(ticketId).build();

        TicketResponse ticketResponse = ticketServiceStub.getBalanceByTicketId(ticketRequest);

        return ticketResponse.getBalance();
    }
}
