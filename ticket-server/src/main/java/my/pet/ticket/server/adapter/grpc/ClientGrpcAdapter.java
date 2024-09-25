package my.pet.ticket.server.adapter.grpc;

import static my.pet.ticket.server.common.utils.GrpcMessageUtils.convertClientToClientResponse;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.List;
import my.pet.ticket.grpc.ClientResponse;
import my.pet.ticket.grpc.ClientResponses;
import my.pet.ticket.grpc.ClientServiceGrpc.ClientServiceImplBase;
import my.pet.ticket.grpc.FilterRequest;
import my.pet.ticket.server.application.domain.model.Client;
import my.pet.ticket.server.application.domain.service.ClientService;
import my.pet.ticket.server.application.domain.service.DomainServiceException;
import my.pet.ticket.server.common.utils.GrpcMessageUtils;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class ClientGrpcAdapter extends ClientServiceImplBase {

  private static final DomainServiceException NULL_FILTER_OR_EMPTY_FIELD = new DomainServiceException(
      "Filter and they field client id shouldn't be empty");

  private final ClientService clientService;

  public ClientGrpcAdapter(ClientService clientService) {
    this.clientService = clientService;
  }

  @Override
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
  public void getAllClients(FilterRequest request,
      StreamObserver<ClientResponses> responseObserver) {
    List<Client> clients = new ArrayList<>();
    if (request.hasFilter()) {
      clients.addAll(this.clientService.getAll(request.getFilter().getPage().getValue(),
          request.getFilter().getPageSize().getValue()));
    } else {
      clients.addAll(this.clientService.getAll());
    }
    List<ClientResponse> clientResponseList = clients.stream()
        .map(GrpcMessageUtils::convertClientToClientResponse).toList();
    ClientResponses clientResponses = ClientResponses.newBuilder()
        .addAllClients(clientResponseList)
        .build();
    responseObserver.onNext(clientResponses);
    responseObserver.onCompleted();
  }

  @Override
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
