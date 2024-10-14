package my.pet.ticket.server.adapter.grpc;

import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import io.grpc.internal.testing.StreamRecorder;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import my.pet.ticket.grpc.Filter;
import my.pet.ticket.grpc.FilterRequest;
import my.pet.ticket.grpc.TicketStatusResponse;
import my.pet.ticket.grpc.TicketStatusResponses;
import my.pet.ticket.server.ApplicationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class TicketStatusGrpcAdapterTest extends ApplicationTest {

  @Autowired
  TicketStatusGrpcAdapter ticketStatusGrpcAdapter;

  @Test
  @SneakyThrows
  void getTicketStatusTest() {
    StreamRecorder<TicketStatusResponse> responseObserver = StreamRecorder.create();
    this.ticketStatusGrpcAdapter.getTicketStatus(FilterRequest.newBuilder()
            .setFilter(Filter.newBuilder().setTicketStatusId(Int64Value.of(1)).build()).build(),
        responseObserver);
    if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
      Assertions.fail("The call did not terminate in time");
    }
    Assertions.assertNull(responseObserver.getError());
    TicketStatusResponse response = responseObserver.getValues().get(0);
    Assertions.assertEquals(response.getTicketStatusId().getValue(), 1L);
  }

  @Test
  @SneakyThrows
  void getAllTicketStatusTest() {
    StreamRecorder<TicketStatusResponses> responseObserver = StreamRecorder.create();
    this.ticketStatusGrpcAdapter.getAllTicketStatuses(FilterRequest.newBuilder().build(),
        responseObserver);
    if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
      Assertions.fail("The call did not terminate in time");
    }
    Assertions.assertNull(responseObserver.getError());
    TicketStatusResponses response = responseObserver.getValues().get(0);
    Assertions.assertFalse(response.getTicketStatusesList().isEmpty());
  }

  @Test
  @SneakyThrows
  void deleteTicketStatusTest() {
    StreamRecorder<Empty> responseObserver = StreamRecorder.create();
    this.ticketStatusGrpcAdapter.deleteTicketStatus(FilterRequest.newBuilder()
            .setFilter(Filter.newBuilder().setTicketStatusId(Int64Value.of(8)).build()).build(),
        responseObserver);
    if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
      Assertions.fail("The call did not terminate in time");
    }
    Assertions.assertNull(responseObserver.getError());
  }

}