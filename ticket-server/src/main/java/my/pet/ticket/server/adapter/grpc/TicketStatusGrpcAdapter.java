package my.pet.ticket.server.adapter.grpc;

import static my.pet.utils.GrpcMessageUtils.convertTicketStatusToTicketStatusResponse;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.List;
import my.pet.ticket.application.domain.model.TicketStatus;
import my.pet.ticket.grpc.FilterRequest;
import my.pet.ticket.grpc.TicketStatusResponse;
import my.pet.ticket.grpc.TicketStatusResponses;
import my.pet.ticket.grpc.TicketStatusServiceGrpc.TicketStatusServiceImplBase;
import my.pet.ticket.server.application.domain.service.DomainServiceException;
import my.pet.ticket.server.application.domain.service.TicketStatusService;
import my.pet.utils.GrpcMessageUtils;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.security.access.annotation.Secured;

@GrpcService
public class TicketStatusGrpcAdapter extends TicketStatusServiceImplBase {

  private static final DomainServiceException NULL_FILTER_OR_EMPTY_FIELD = new DomainServiceException(
      "Filter and they field ticket status id shouldn't be empty");

  private final TicketStatusService ticketStatusService;

  public TicketStatusGrpcAdapter(TicketStatusService ticketStatusService) {
    this.ticketStatusService = ticketStatusService;
  }

  @Override
  @Secured({"MANAGER", "ADMIN"})
  public void getTicketStatus(FilterRequest request,
      StreamObserver<TicketStatusResponse> responseObserver) {
    if (request.hasFilter() && request.getFilter().hasTicketStatusId()) {
      TicketStatus ticketStatus = this.ticketStatusService.get(
          request.getFilter().getTicketStatusId().getValue());
      TicketStatusResponse ticketStatusResponse = convertTicketStatusToTicketStatusResponse(
          ticketStatus);
      responseObserver.onNext(ticketStatusResponse);
      responseObserver.onCompleted();
      return;
    }
    throw NULL_FILTER_OR_EMPTY_FIELD;
  }

  @Override
  @Secured({"MANAGER", "ADMIN"})
  public void getAllTicketStatuses(FilterRequest request,
      StreamObserver<TicketStatusResponses> responseObserver) {
    List<TicketStatus> ticketStatuses = new ArrayList<>();
    if (request.hasFilter()) {
      ticketStatuses.addAll(
          this.ticketStatusService.getAll(request.getFilter().getPage().getValue(),
              request.getFilter().getPageSize().getValue()));
    } else {
      ticketStatuses.addAll(this.ticketStatusService.getAll());
    }
    List<TicketStatusResponse> ticketStatusResponseList = ticketStatuses.stream().map(
        GrpcMessageUtils::convertTicketStatusToTicketStatusResponse).toList();
    TicketStatusResponses ticketStatusResponses = TicketStatusResponses.newBuilder()
        .addAllTicketStatuses(ticketStatusResponseList)
        .build();
    responseObserver.onNext(ticketStatusResponses);
    responseObserver.onCompleted();
  }

  @Override
  @Secured("ADMIN")
  public void deleteTicketStatus(FilterRequest request, StreamObserver<Empty> responseObserver) {
    if (request.hasFilter() && request.getFilter().hasTicketStatusId()) {
      this.ticketStatusService.delete(request.getFilter().getTicketStatusId().getValue());
      responseObserver.onNext(Empty.getDefaultInstance());
      responseObserver.onCompleted();
      return;
    }
    throw NULL_FILTER_OR_EMPTY_FIELD;
  }

}
