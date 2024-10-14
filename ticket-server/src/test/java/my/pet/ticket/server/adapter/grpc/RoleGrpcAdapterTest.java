package my.pet.ticket.server.adapter.grpc;

import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import io.grpc.internal.testing.StreamRecorder;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import my.pet.ticket.grpc.Filter;
import my.pet.ticket.grpc.FilterRequest;
import my.pet.ticket.grpc.RoleResponse;
import my.pet.ticket.grpc.RoleResponses;
import my.pet.ticket.server.ApplicationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class RoleGrpcAdapterTest extends ApplicationTest {

  @Autowired
  RoleGrpcAdapter roleGrpcAdapter;

  @Test
  @SneakyThrows
  void getRoleTest() {
    StreamRecorder<RoleResponse> responseObserver = StreamRecorder.create();
    this.roleGrpcAdapter.getRole(FilterRequest.newBuilder()
            .setFilter(Filter.newBuilder().setRoleId(Int64Value.of(1)).build()).build(),
        responseObserver);
    if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
      Assertions.fail("The call did not terminate in time");
    }
    Assertions.assertNull(responseObserver.getError());
    RoleResponse response = responseObserver.getValues().get(0);
    Assertions.assertEquals(response.getRoleId().getValue(), 1L);
  }

  @Test
  @SneakyThrows
  void getAllRolesTest() {
    StreamRecorder<RoleResponses> responseObserver = StreamRecorder.create();
    this.roleGrpcAdapter.getAllRoles(FilterRequest.newBuilder().build(),
        responseObserver);
    if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
      Assertions.fail("The call did not terminate in time");
    }
    Assertions.assertNull(responseObserver.getError());
    RoleResponses response = responseObserver.getValues().get(0);
    Assertions.assertFalse(response.getRolesList().isEmpty());
  }

  @Test
  @SneakyThrows
  void deleteRoleTest() {
    StreamRecorder<Empty> responseObserver = StreamRecorder.create();
    this.roleGrpcAdapter.deleteRole(FilterRequest.newBuilder()
            .setFilter(Filter.newBuilder().setRoleId(Int64Value.of(8)).build()).build(),
        responseObserver);
    if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
      Assertions.fail("The call did not terminate in time");
    }
    Assertions.assertNull(responseObserver.getError());
  }

}