package my.pet.ticket.server.adapter.grpc;

import com.google.protobuf.BoolValue;
import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import com.google.protobuf.StringValue;
import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.List;
import my.pet.ticket.grpc.FilterRequest;
import my.pet.ticket.grpc.RegisterUserRequest;
import my.pet.ticket.grpc.RoleResponse;
import my.pet.ticket.grpc.UserResponse;
import my.pet.ticket.grpc.UserResponses;
import my.pet.ticket.grpc.UserServiceGrpc.UserServiceImplBase;
import my.pet.ticket.server.application.domain.model.User;
import my.pet.ticket.server.application.domain.service.DomainServiceException;
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

  @Override
  public void activeUser(FilterRequest request, StreamObserver<UserResponse> responseObserver) {
    if (request.hasFilter() && request.getFilter().hasUserId()) {
      User user = this.userService.activateUser(request.getFilter().getUserId().getValue());
      UserResponse userResponse = convertUserToUserResponse(user);
      responseObserver.onNext(userResponse);
      responseObserver.onCompleted();
    } else {
      throw new DomainServiceException("Filter and they field user id shouldn't be empty");
    }
  }

  @Override
  public void getUser(FilterRequest request, StreamObserver<UserResponse> responseObserver) {
    if (request.hasFilter() && request.getFilter().hasUserId()) {
      User user = this.userService.getUser(request.getFilter().getUserId().getValue());
      UserResponse userResponse = convertUserToUserResponse(user);
      responseObserver.onNext(userResponse);
      responseObserver.onCompleted();
    } else {
      throw new DomainServiceException("Filter and they field user id shouldn't be empty");
    }
  }

  @Override
  public void getAllUsers(FilterRequest request, StreamObserver<UserResponses> responseObserver) {
    List<User> users = new ArrayList<>();
    if (request.hasFilter()) {
      users.addAll(this.userService.getAllUsers(request.getFilter().getPage().getValue(),
          request.getFilter().getPageSize().getValue()));
    } else {
      users.addAll(this.userService.getAllUsers());
    }
    List<UserResponse> userResponses = users.stream().map(this::convertUserToUserResponse).toList();
    responseObserver.onNext(UserResponses.newBuilder().addAllUsers(userResponses).build());
    responseObserver.onCompleted();
  }

  @Override
  public void suspendUser(FilterRequest request, StreamObserver<UserResponse> responseObserver) {
    if (request.hasFilter() && request.getFilter().hasUserId()) {
      User user = this.userService.suspendUser(request.getFilter().getUserId().getValue());
      UserResponse userResponse = convertUserToUserResponse(user);
      responseObserver.onNext(userResponse);
      responseObserver.onCompleted();
    } else {
      throw new DomainServiceException("Filter and they field user id shouldn't be empty");
    }
  }

  @Override
  public void deleteUser(FilterRequest request, StreamObserver<Empty> responseObserver) {
    if (request.hasFilter() && request.getFilter().hasUserId()) {
      this.userService.deleteUser(request.getFilter().getUserId().getValue());
      responseObserver.onNext(Empty.getDefaultInstance());
      responseObserver.onCompleted();
    } else {
      throw new DomainServiceException("Filter and they field user id shouldn't be empty");
    }
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
