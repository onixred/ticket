package my.pet.ticket.grpc.server;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import my.pet.ticket.grpc.TicketRequest;
import my.pet.ticket.grpc.TicketResponse;
import my.pet.ticket.grpc.TicketServiceGrpc;
import my.pet.ticket.mock.TicketMockService;
import my.pet.ticket.model.model.BalanceMock;
import org.springframework.stereotype.Service;

/**
 * TicketServiceGrpc класс grpc - вызывает методы и возвращает данные клиенту
 *
 * @author <a href="mailto:baranov.alexalex@gmail.com">abaranov</a>
 */
@Service
@RequiredArgsConstructor
public class TicketServiceGrpcImpl extends TicketServiceGrpc.TicketServiceImplBase {

    private final TicketMockService ticketMockService;

    @Override
    public void getBalanceByTicketId(TicketRequest ticketRequest, StreamObserver<TicketResponse> responseObserver) {

        BalanceMock balanceMock = ticketMockService.getBalanceMockById(ticketRequest.getTicketId());

        if (balanceMock == null) {
            responseObserver.onError(
                    (Status.NOT_FOUND.withDescription("Задача  c id " + ticketRequest.getTicketId()  + " не найдена"))
                    .asRuntimeException()
            );
            return;
        }

        TicketResponse ticketResponse = TicketResponse.newBuilder()
                .setTicketId(balanceMock.getTicketId())
                .setBalance(balanceMock.getBalance())
                .build();

        responseObserver.onNext(ticketResponse);
        responseObserver.onCompleted();
    }
}
