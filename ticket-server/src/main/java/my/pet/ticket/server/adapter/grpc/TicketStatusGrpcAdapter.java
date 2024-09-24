package my.pet.ticket.server.adapter.grpc;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import my.pet.ticket.grpc.FilterRequest;
import my.pet.ticket.grpc.TicketStatusResponse;
import my.pet.ticket.grpc.TicketStatusResponses;
import my.pet.ticket.grpc.TicketStatusServiceGrpc.TicketStatusServiceImplBase;
import my.pet.ticket.server.application.domain.service.TicketStatusService;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class TicketStatusGrpcAdapter extends TicketStatusServiceImplBase {

  private final TicketStatusService ticketStatusService;

  public TicketStatusGrpcAdapter(TicketStatusService ticketStatusService) {
    this.ticketStatusService = ticketStatusService;
  }

  @Override
  public void getTicketStatus(FilterRequest request,
      StreamObserver<TicketStatusResponse> responseObserver) {
    super.getTicketStatus(request, responseObserver);
  }

  @Override
  public void getAllTicketStatuses(FilterRequest request,
      StreamObserver<TicketStatusResponses> responseObserver) {
    super.getAllTicketStatuses(request, responseObserver);
  }

  @Override
  public void deleteTicketStatus(FilterRequest request, StreamObserver<Empty> responseObserver) {
    super.deleteTicketStatus(request, responseObserver);
  }

}
