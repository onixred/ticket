package my.pet.ticket.server.adapter.grpc;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import my.pet.ticket.grpc.ClientResponse;
import my.pet.ticket.grpc.ClientResponses;
import my.pet.ticket.grpc.ClientServiceGrpc.ClientServiceImplBase;
import my.pet.ticket.grpc.FilterRequest;
import my.pet.ticket.server.application.domain.service.ClientService;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class ClientGrpcAdapter extends ClientServiceImplBase {

  private final ClientService clientService;

  public ClientGrpcAdapter(ClientService clientService) {
    this.clientService = clientService;
  }

  @Override
  public void getClient(FilterRequest request, StreamObserver<ClientResponse> responseObserver) {
    super.getClient(request, responseObserver);
  }

  @Override
  public void getAllClients(FilterRequest request,
      StreamObserver<ClientResponses> responseObserver) {
    super.getAllClients(request, responseObserver);
  }

  @Override
  public void deleteClient(FilterRequest request, StreamObserver<Empty> responseObserver) {
    super.deleteClient(request, responseObserver);
  }

}
