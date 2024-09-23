package my.pet.ticket.server.adapter.grpc;

import com.google.protobuf.BoolValue;
import com.google.protobuf.Int64Value;
import com.google.protobuf.StringValue;
import io.grpc.stub.StreamObserver;
import my.pet.ticket.grpc.RegisterUserRequest;
import my.pet.ticket.grpc.RoleResponse;
import my.pet.ticket.grpc.UserResponse;
import my.pet.ticket.grpc.UserServiceGrpc.UserServiceImplBase;
import my.pet.ticket.server.application.domain.model.User;
import my.pet.ticket.server.application.domain.service.UserService;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class UserGrpcAdapter extends UserServiceImplBase {

  private final UserService userService;

  public UserGrpcAdapter(UserService userService) {
    this.userService = userService;
  }

  @Override
  public void registerUser(RegisterUserRequest request,
      StreamObserver<UserResponse> responseObserver) {
    User user = this.userService.registerUser(
        builder -> builder.fullName(request.getFullName().getValue())
            .login(request.getLogin().getValue()).password(request.getPassword().getValue())
            .build());
    UserResponse response = convertUserToUserResponse(user);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  private UserResponse convertUserToUserResponse(User user) {
    return UserResponse.newBuilder().setUserId(Int64Value.of(user.getUserId())).setRole(
            RoleResponse.newBuilder().setRoleId(Int64Value.of(user.getRole().getRoleId()))
                .setName(StringValue.of(user.getRole().getName())).build())
        .setFullName(StringValue.of(user.getFullName())).setLogin(StringValue.of(user.getLogin()))
        .setActive(BoolValue.of(user.getActive())).setSuspended(BoolValue.of(user.getSuspended()))
        .build();
  }

}
