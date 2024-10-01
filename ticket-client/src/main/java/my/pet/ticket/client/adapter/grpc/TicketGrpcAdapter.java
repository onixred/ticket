package my.pet.ticket.client.adapter.grpc;

import com.google.protobuf.Empty;
import java.util.List;
import my.pet.ticket.application.domain.model.Ticket;
import my.pet.ticket.grpc.FilterRequest;
import my.pet.ticket.grpc.TicketServiceGrpc.TicketServiceBlockingStub;
import my.pet.utils.GrpcMessageUtils;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.client.security.CallCredentialsHelper;
import org.springframework.stereotype.Component;

@Component
public class TicketGrpcAdapter {

  @GrpcClient("globalClient")
  private TicketServiceBlockingStub ticketServiceBlockingStub;

  public List<Ticket> getAllTickets(String token) {
    return this.ticketServiceBlockingStub
        .withCallCredentials(CallCredentialsHelper.authorizationHeader(token))
        .getAllTickets(FilterRequest.newBuilder().setEmpty(Empty.getDefaultInstance()).build())
        .getTicketsList()
        .stream()
        .map(GrpcMessageUtils::convertTicketResponseToTicket)
        .toList();

  }

}
