package my.pet.ticket.client.adapter.grpc;

import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import java.util.List;
import my.pet.ticket.application.domain.model.Client;
import my.pet.ticket.application.domain.model.Clients;
import my.pet.ticket.client.application.port.api.ClientGrpcPort;
import my.pet.ticket.grpc.ClientResponses;
import my.pet.ticket.grpc.ClientServiceGrpc.ClientServiceBlockingStub;
import my.pet.ticket.grpc.Filter;
import my.pet.ticket.grpc.FilterRequest;
import my.pet.utils.GrpcMessageUtils;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.client.security.CallCredentialsHelper;
import org.springframework.stereotype.Component;

@Component
public class ClientGrpcAdapter implements ClientGrpcPort {

  @GrpcClient("globalClient")
  private ClientServiceBlockingStub clientServiceBlockingStub;

  @Override
  public List<Client> getAllClients(String token) {
    return clientServiceBlockingStub.withCallCredentials(
            CallCredentialsHelper.authorizationHeader(token))
        .getAllClients(FilterRequest.newBuilder().setEmpty(Empty.getDefaultInstance()).build())
        .getClientsList().stream().map(GrpcMessageUtils::convertClientResponseToClient).toList();
  }

  @Override
  public Clients getAllClients(String token, Integer page, Integer pageSize) {
    ClientResponses clientResponses = this.clientServiceBlockingStub.withCallCredentials(
        CallCredentialsHelper.authorizationHeader(token)).getAllClients(FilterRequest.newBuilder()
        .setFilter(
            Filter.newBuilder().setPage(Int32Value.of(page)).setPageSize(Int32Value.of(pageSize))
                .build()).build());
    List<Client> clients = clientResponses.getClientsList().stream()
        .map(GrpcMessageUtils::convertClientResponseToClient).toList();
    return new Clients(clients, clientResponses.getPages().getValue());
  }
}
