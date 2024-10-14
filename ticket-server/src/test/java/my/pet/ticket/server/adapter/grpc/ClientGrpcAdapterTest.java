package my.pet.ticket.server.adapter.grpc;

import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import io.grpc.internal.testing.StreamRecorder;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import my.pet.ticket.grpc.ClientResponse;
import my.pet.ticket.grpc.ClientResponses;
import my.pet.ticket.grpc.Filter;
import my.pet.ticket.grpc.FilterRequest;
import my.pet.ticket.server.ApplicationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ClientGrpcAdapterTest extends ApplicationTest {

  @Autowired
  ClientGrpcAdapter clientGrpcAdapter;

  @Test
  @SneakyThrows
  void getClientTest() {
    StreamRecorder<ClientResponse> responseObserver = StreamRecorder.create();
    this.clientGrpcAdapter.getClient(FilterRequest.newBuilder()
            .setFilter(Filter.newBuilder().setClientId(Int64Value.of(1001)).build()).build(),
        responseObserver);
    if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
      Assertions.fail("The call did not terminate in time");
    }
    Assertions.assertNull(responseObserver.getError());
    ClientResponse response = responseObserver.getValues().get(0);
    Assertions.assertEquals(response.getClientId().getValue(), 1001L);
  }

  @Test
  @SneakyThrows
  void getAllClientsTest() {
    StreamRecorder<ClientResponses> responseObserver = StreamRecorder.create();
    this.clientGrpcAdapter.getAllClients(FilterRequest.newBuilder().build(),
        responseObserver);
    if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
      Assertions.fail("The call did not terminate in time");
    }
    Assertions.assertNull(responseObserver.getError());
    ClientResponses response = responseObserver.getValues().get(0);
    Assertions.assertFalse(response.getClientsList().isEmpty());
  }

  @Test
  @SneakyThrows
  void deleteClientTest() {
    StreamRecorder<Empty> responseObserver = StreamRecorder.create();
    this.clientGrpcAdapter.deleteClient(FilterRequest.newBuilder()
            .setFilter(Filter.newBuilder().setClientId(Int64Value.of(1005)).build()).build(),
        responseObserver);
    if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
      Assertions.fail("The call did not terminate in time");
    }
    Assertions.assertNull(responseObserver.getError());
  }

}