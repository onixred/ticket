package my.pet.ticket.server.adapter.grpc;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import my.pet.ticket.grpc.FilterRequest;
import my.pet.ticket.grpc.TicketServiceGrpc.TicketServiceImplBase;
import my.pet.ticket.grpc.TicketStatusResponse;
import my.pet.ticket.grpc.TicketStatusResponses;
import my.pet.ticket.server.application.domain.service.TicketService;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class TicketGrpcAdapter extends TicketServiceImplBase {

  private final TicketService ticketService;

  public TicketGrpcAdapter(TicketService ticketService) {
    this.ticketService = ticketService;
  }

  @Override
  public void getTicket(FilterRequest request,
      StreamObserver<TicketStatusResponse> responseObserver) {
    super.getTicket(request, responseObserver);
  }

  @Override
  public void getAllTickets(FilterRequest request,
      StreamObserver<TicketStatusResponses> responseObserver) {
    super.getAllTickets(request, responseObserver);
  }

  @Override
  public void deleteTicket(FilterRequest request, StreamObserver<Empty> responseObserver) {
    super.deleteTicket(request, responseObserver);
  }

}
