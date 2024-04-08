package my.pet.ticket.sevice;


import io.grpc.Metadata;
import io.grpc.examples.proto.*;
import io.grpc.protobuf.ProtoUtils;
import io.grpc.stub.StreamObserver;
import my.pet.ticket.entity.Ticket;
import my.pet.ticket.repository.TicketRepository;
import net.devh.boot.grpc.server.service.GrpcService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static io.grpc.Status.NOT_FOUND;

@GrpcService
public class TicketService extends TicketServiceGrpc.TicketServiceImplBase {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void getBalanceByTicketId(TicketRequest request, StreamObserver<ResponseTicket> responseObserver) {
        super.getBalanceByTicketId(request, responseObserver);
        List<Ticket> list = ticketRepository.findAllByIdIn(request.getTicketIdList());

        if (list.isEmpty()) {
            Metadata.Key<ErrorMessageGrpc> messageGrpcKey = ProtoUtils.keyForProto(ErrorMessageGrpc.getDefaultInstance());
            ErrorMessageGrpc response = ErrorMessageGrpc.newBuilder().setCode(404).setMessage("не найдено тикеты по id").build();
            Metadata metadata = new Metadata();
            metadata.put(messageGrpcKey, response);
            responseObserver.onError(NOT_FOUND.asException(metadata));
        } else {
            List<TicketDto> tickets = list.stream().map(ticket -> modelMapper.map(ticket, TicketDto.class)).toList();
            ResponseTicket responseTicket = ResponseTicket.newBuilder().addAllList(tickets).build();
            responseObserver.onNext(responseTicket);
            responseObserver.onCompleted();
        }
    }
}
