package my.pet.ticket.server.adapter.grpc;

import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import io.grpc.internal.testing.StreamRecorder;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import my.pet.ticket.grpc.Filter;
import my.pet.ticket.grpc.FilterRequest;
import my.pet.ticket.grpc.TicketResponse;
import my.pet.ticket.grpc.TicketResponses;
import my.pet.ticket.server.ApplicationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class TicketGrpcAdapterTest extends ApplicationTest {

  @Autowired
  TicketGrpcAdapter ticketGrpcAdapter;

  @Test
  @SneakyThrows
  void getTicketTest() {
    StreamRecorder<TicketResponse> responseObserver = StreamRecorder.create();
    this.ticketGrpcAdapter.getTicket(FilterRequest.newBuilder()
            .setFilter(Filter.newBuilder().setTicketId(Int64Value.of(10001)).build()).build(),
        responseObserver);
    if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
      Assertions.fail("The call did not terminate in time");
    }
    Assertions.assertNull(responseObserver.getError());
    TicketResponse response = responseObserver.getValues().get(0);
    Assertions.assertEquals(response.getTicketId().getValue(), 10001L);
  }

  @Test
  @SneakyThrows
  void getAllTicketsTest() {
    StreamRecorder<TicketResponses> responseObserver = StreamRecorder.create();
    this.ticketGrpcAdapter.getAllTickets(FilterRequest.newBuilder().build(),
        responseObserver);
    if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
      Assertions.fail("The call did not terminate in time");
    }
    Assertions.assertNull(responseObserver.getError());
    TicketResponses response = responseObserver.getValues().get(0);
    Assertions.assertFalse(response.getTicketsList().isEmpty());
  }

  @Test
  @SneakyThrows
  void deleteTicketTest() {
    StreamRecorder<Empty> responseObserver = StreamRecorder.create();
    this.ticketGrpcAdapter.deleteTicket(FilterRequest.newBuilder()
            .setFilter(Filter.newBuilder().setTicketId(Int64Value.of(10005)).build()).build(),
        responseObserver);
    if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
      Assertions.fail("The call did not terminate in time");
    }
    Assertions.assertNull(responseObserver.getError());
  }

}