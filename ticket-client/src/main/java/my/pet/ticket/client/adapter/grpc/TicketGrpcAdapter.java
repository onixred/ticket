package my.pet.ticket.client.adapter.grpc;

import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import java.util.List;
import my.pet.ticket.application.domain.model.Ticket;
import my.pet.ticket.client.application.domain.model.Tickets;
import my.pet.ticket.client.application.port.api.TicketGrpcPort;
import my.pet.ticket.grpc.Filter;
import my.pet.ticket.grpc.FilterRequest;
import my.pet.ticket.grpc.TicketResponses;
import my.pet.ticket.grpc.TicketServiceGrpc.TicketServiceBlockingStub;
import my.pet.utils.GrpcMessageUtils;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.client.security.CallCredentialsHelper;
import org.springframework.stereotype.Component;

@Component
public class TicketGrpcAdapter implements TicketGrpcPort {

  @GrpcClient("globalClient")
  private TicketServiceBlockingStub ticketServiceBlockingStub;

  public List<Ticket> getAllTickets(String token) {
    return this.ticketServiceBlockingStub.withCallCredentials(
            CallCredentialsHelper.authorizationHeader(token))
        .getAllTickets(FilterRequest.newBuilder().setEmpty(Empty.getDefaultInstance()).build())
        .getTicketsList().stream().map(GrpcMessageUtils::convertTicketResponseToTicket).toList();

  }

  @Override
  public Tickets getAllTickets(String token, Integer page, Integer pageSize) {
    TicketResponses ticketResponses = this.ticketServiceBlockingStub.withCallCredentials(
        CallCredentialsHelper.authorizationHeader(token)).getAllTickets(FilterRequest.newBuilder()
        .setFilter(
            Filter.newBuilder().setPage(Int32Value.of(page)).setPageSize(Int32Value.of(pageSize))
                .build()).build());
    List<Ticket> tickets = ticketResponses.getTicketsList().stream()
        .map(GrpcMessageUtils::convertTicketResponseToTicket).toList();
    return new Tickets(tickets, ticketResponses.getPages().getValue());
  }

}