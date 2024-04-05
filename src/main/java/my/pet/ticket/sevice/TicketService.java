package my.pet.ticket.sevice;


import io.grpc.examples.proto.ResponseTicket;
import io.grpc.examples.proto.TicketDto;
import io.grpc.examples.proto.TicketRequest;
import io.grpc.examples.proto.TicketServiceGrpc;
import io.grpc.stub.StreamObserver;
import my.pet.ticket.entity.Ticket;
import my.pet.ticket.repository.TicketRepository;
import net.devh.boot.grpc.server.service.GrpcService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@GrpcService
public class TicketService extends TicketServiceGrpc.TicketServiceImplBase {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void getBalanceByTicketId(TicketRequest request, StreamObserver<ResponseTicket> responseObserver) {
        super.getBalanceByTicketId(request, responseObserver);
        List<Long> requestIds = request.getTicketIdList();
        List<Ticket> list = ticketRepository.findAllByIdIn(requestIds);

        List<TicketDto> ticketDtos = list.stream().map(ticket -> modelMapper.map(ticket, TicketDto.class)).toList();

        ResponseTicket responseTicket = ResponseTicket.newBuilder().addAllList(ticketDtos).build();

        responseObserver.onNext(responseTicket);
        responseObserver.onCompleted();

    }
}
