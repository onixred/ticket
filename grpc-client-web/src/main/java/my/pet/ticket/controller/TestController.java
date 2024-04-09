package my.pet.ticket.controller;

import io.grpc.ForwardingChannelBuilder2;
import io.grpc.examples.proto.ResponseTicket;
import io.grpc.examples.proto.TicketRequest;
import io.grpc.examples.proto.TicketServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController("/rrr")
public class TestController {

//    ForwardingChannelBuilder2 forwardingChannelBuilder2;

    @GrpcClient("ticket-stub")
    TicketServiceGrpc.TicketServiceFutureStub stub;

    @PostMapping("/dd")
    public void tets() throws ExecutionException, InterruptedException {
        TicketRequest request = TicketRequest.newBuilder()
                .addTicketId(1)
                .build();
        ResponseTicket responseTicket =  stub.getBalanceByTicketId(request).get();
        System.out.println(responseTicket);
    }

}
