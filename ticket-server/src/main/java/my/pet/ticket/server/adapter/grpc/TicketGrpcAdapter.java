package my.pet.ticket.server.adapter.grpc;

import static my.pet.utils.GrpcMessageUtils.convertTicketToTicketResponse;

import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import io.grpc.stub.StreamObserver;
import java.util.List;
import my.pet.ticket.application.domain.model.Ticket;
import my.pet.ticket.grpc.FilterRequest;
import my.pet.ticket.grpc.TicketResponse;
import my.pet.ticket.grpc.TicketResponses;
import my.pet.ticket.grpc.TicketServiceGrpc.TicketServiceImplBase;
import my.pet.ticket.server.application.domain.service.DomainServiceException;
import my.pet.ticket.server.application.domain.service.TicketService;
import my.pet.utils.GrpcMessageUtils;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;

@GrpcService
public class TicketGrpcAdapter extends TicketServiceImplBase {

  private static final DomainServiceException NULL_FILTER_OR_EMPTY_FIELD = new DomainServiceException(
      "Filter and they field ticket id shouldn't be empty");

  private final TicketService ticketService;

  public TicketGrpcAdapter(TicketService ticketService) {
    this.ticketService = ticketService;
  }

  @Override
  @Secured({"MANAGER", "ADMIN"})
  public void getTicket(FilterRequest request,
      StreamObserver<TicketResponse> responseObserver) {
    if (request.hasFilter() && request.getFilter().hasTicketId()) {
      Ticket ticket = this.ticketService.get(request.getFilter().getTicketId().getValue());
      TicketResponse ticketResponse = convertTicketToTicketResponse(ticket);
      responseObserver.onNext(ticketResponse);
      responseObserver.onCompleted();
      return;
    }
    throw NULL_FILTER_OR_EMPTY_FIELD;
  }

  @Override
  @Secured({"MANAGER", "ADMIN"})
  public void getAllTickets(FilterRequest request,
      StreamObserver<TicketResponses> responseObserver) {
    Page<Ticket> tickets;
    if (request.hasFilter()) {
      tickets = this.ticketService.getAll(request.getFilter().getPage().getValue(),
          request.getFilter().getPageSize().getValue());
    } else {
      tickets = this.ticketService.getAll();
    }
    List<TicketResponse> ticketResponseList = tickets.stream()
        .map(GrpcMessageUtils::convertTicketToTicketResponse).toList();
    TicketResponses ticketResponses = TicketResponses.newBuilder()
        .addAllTickets(ticketResponseList)
        .setPages(Int64Value.of(request.getFilter().getPage().getValue()))
        .setPages(Int64Value.of(tickets.getTotalPages()))
        .build();
    responseObserver.onNext(ticketResponses);
    responseObserver.onCompleted();
    ;
  }

  @Override
  @Secured("ADMIN")
  public void deleteTicket(FilterRequest request, StreamObserver<Empty> responseObserver) {
    if (request.hasFilter() && request.getFilter().hasTicketId()) {
      this.ticketService.delete(request.getFilter().getTicketId().getValue());
      responseObserver.onNext(Empty.getDefaultInstance());
      responseObserver.onCompleted();
      return;
    }
    throw NULL_FILTER_OR_EMPTY_FIELD;
  }

}
