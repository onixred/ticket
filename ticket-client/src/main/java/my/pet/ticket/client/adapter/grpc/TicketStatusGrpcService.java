package my.pet.ticket.client.adapter.grpc;

import com.google.protobuf.Empty;
import java.util.List;
import my.pet.ticket.application.domain.model.TicketStatus;
import my.pet.ticket.grpc.FilterRequest;
import my.pet.ticket.grpc.TicketStatusServiceGrpc.TicketStatusServiceBlockingStub;
import my.pet.utils.GrpcMessageUtils;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.client.security.CallCredentialsHelper;
import org.springframework.stereotype.Component;

@Component
public class TicketStatusGrpcService {

  @GrpcClient("globalClient")
  private TicketStatusServiceBlockingStub ticketStatusServiceBlockingStub;

  public List<TicketStatus> getAllTicketStatuses(String token) {
    return this.ticketStatusServiceBlockingStub.withCallCredentials(
            CallCredentialsHelper.authorizationHeader(token))
        .getAllTicketStatuses(FilterRequest.newBuilder().setEmpty(
            Empty.getDefaultInstance()).build())
        .getTicketStatusesList()
        .stream()
        .map(GrpcMessageUtils::convertTicketStatusResponseToTicketStatus)
        .toList();
  }

}
