package my.pet.ticket.server.adapter.grpc;

import static my.pet.utils.GrpcMessageUtils.convertClientToClientResponse;

import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import io.grpc.stub.StreamObserver;
import java.util.List;
import my.pet.ticket.application.domain.model.Client;
import my.pet.ticket.grpc.ClientResponse;
import my.pet.ticket.grpc.ClientResponses;
import my.pet.ticket.grpc.ClientServiceGrpc.ClientServiceImplBase;
import my.pet.ticket.grpc.FilterRequest;
import my.pet.ticket.server.application.domain.service.ClientService;
import my.pet.ticket.server.application.domain.service.DomainServiceException;
import my.pet.utils.GrpcMessageUtils;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;

@GrpcService
public class ClientGrpcAdapter extends ClientServiceImplBase {

  private static final DomainServiceException NULL_FILTER_OR_EMPTY_FIELD = new DomainServiceException(
      "Filter and they field client id shouldn't be empty");

  private final ClientService clientService;

  public ClientGrpcAdapter(ClientService clientService) {
    this.clientService = clientService;
  }

  @Override
  @Secured({"MANAGER", "ADMIN"})
  public void getClient(FilterRequest request, StreamObserver<ClientResponse> responseObserver) {
    if (request.hasFilter() && request.getFilter().hasClientId()) {
      Client client = this.clientService.get(request.getFilter().getClientId().getValue());
      ClientResponse clientResponse = convertClientToClientResponse(client);
      responseObserver.onNext(clientResponse);
      responseObserver.onCompleted();
      return;
    }
    throw NULL_FILTER_OR_EMPTY_FIELD;
  }

  @Override
  @Secured({"MANAGER", "ADMIN"})
  public void getAllClients(FilterRequest request,
      StreamObserver<ClientResponses> responseObserver) {
    Page<Client> clients;
    if (request.hasFilter()) {
      clients = this.clientService.getAll(request.getFilter().getPage().getValue(),
          request.getFilter().getPageSize().getValue());
    } else {
      clients = this.clientService.getAll();
    }
    List<ClientResponse> clientResponseList = clients.stream()
        .map(GrpcMessageUtils::convertClientToClientResponse).toList();
    ClientResponses clientResponses = ClientResponses.newBuilder()
        .addAllClients(clientResponseList)
        .setPages(Int64Value.of(clients.getTotalPages()))
        .build();
    responseObserver.onNext(clientResponses);
    responseObserver.onCompleted();
  }

  @Override
  @Secured("ADMIN")
  public void deleteClient(FilterRequest request, StreamObserver<Empty> responseObserver) {
    if (request.hasFilter() && request.getFilter().hasClientId()) {
      this.clientService.delete(request.getFilter().getClientId().getValue());
      responseObserver.onNext(Empty.getDefaultInstance());
      responseObserver.onCompleted();
      return;
    }
    throw NULL_FILTER_OR_EMPTY_FIELD;
  }

}
