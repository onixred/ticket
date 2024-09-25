package my.pet.ticket.server.adapter.grpc;

import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import com.google.protobuf.StringValue;
import io.grpc.internal.testing.StreamRecorder;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import my.pet.ticket.grpc.Filter;
import my.pet.ticket.grpc.FilterRequest;
import my.pet.ticket.grpc.RegisterUserRequest;
import my.pet.ticket.grpc.UserResponse;
import my.pet.ticket.grpc.UserResponses;
import my.pet.ticket.server.ApplicationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class UserGrpcAdapterTest extends ApplicationTest {

  @Autowired
  UserGrpcAdapter userGrpcAdapter;

  @Test
  @SneakyThrows
  void registerUserTest() {
    StreamRecorder<UserResponse> responseObserver = StreamRecorder.create();
    this.userGrpcAdapter.registerUser(
        RegisterUserRequest.newBuilder().setFullName(StringValue.of("Eryomin Egor Konstantinovich"))
            .setLogin(StringValue.of("logintest12345")).setPassword(StringValue.of("passwordhash"))
            .build(), responseObserver);
    if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
      Assertions.fail("The call did not terminate in time");
    }
    Assertions.assertNull(responseObserver.getError());
    UserResponse response = responseObserver.getValues().get(0);
    Assertions.assertEquals(1007L, response.getUserId().getValue());
  }

  @Test
  @SneakyThrows
  void activateUserTest() {
    StreamRecorder<UserResponse> responseObserver = StreamRecorder.create();
    this.userGrpcAdapter.activeUser(FilterRequest.newBuilder()
            .setFilter(Filter.newBuilder().setUserId(Int64Value.of(1004)).build()).build(),
        responseObserver);
    if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
      Assertions.fail("The call did not terminate in time");
    }
    Assertions.assertNull(responseObserver.getError());
    UserResponse response = responseObserver.getValues().get(0);
    Assertions.assertEquals(response.getUserId().getValue(), 1004L);
    Assertions.assertTrue(response.getActive().getValue());
  }

  @Test
  @SneakyThrows
  void suspendUserTest() {
    StreamRecorder<UserResponse> responseObserver = StreamRecorder.create();
    this.userGrpcAdapter.suspendUser(FilterRequest.newBuilder()
            .setFilter(Filter.newBuilder().setUserId(Int64Value.of(1005)).build()).build(),
        responseObserver);
    if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
      Assertions.fail("The call did not terminate in time");
    }
    Assertions.assertNull(responseObserver.getError());
    UserResponse response = responseObserver.getValues().get(0);
    Assertions.assertEquals(response.getUserId().getValue(), 1005L);
    Assertions.assertTrue(response.getSuspended().getValue());
  }

  @Test
  @SneakyThrows
  void getUserTest() {
    StreamRecorder<UserResponse> responseObserver = StreamRecorder.create();
    this.userGrpcAdapter.getUser(FilterRequest.newBuilder()
            .setFilter(Filter.newBuilder().setUserId(Int64Value.of(1001)).build()).build(),
        responseObserver);
    if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
      Assertions.fail("The call did not terminate in time");
    }
    Assertions.assertNull(responseObserver.getError());
    UserResponse response = responseObserver.getValues().get(0);
    Assertions.assertEquals(response.getUserId().getValue(), 1001L);
  }

  @Test
  @SneakyThrows
  void getAllUsersTest() {
    StreamRecorder<UserResponses> responseObserver = StreamRecorder.create();
    this.userGrpcAdapter.getAllUsers(
        FilterRequest.newBuilder().setFilter(Filter.newBuilder().build()).build(),
        responseObserver);
    if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
      Assertions.fail("The call did not terminate in time");
    }
    Assertions.assertNull(responseObserver.getError());
    UserResponses response = responseObserver.getValues().get(0);
    Assertions.assertFalse(response.getUsersList().isEmpty());
  }

  @Test
  @SneakyThrows
  void deleteUserTest() {
    StreamRecorder<Empty> responseObserver = StreamRecorder.create();
    this.userGrpcAdapter.deleteUser(FilterRequest.newBuilder()
            .setFilter(Filter.newBuilder().setUserId(Int64Value.of(1006)).build()).build(),
        responseObserver);
    if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
      Assertions.fail("The call did not terminate in time");
    }
    Assertions.assertNull(responseObserver.getError());
  }
}