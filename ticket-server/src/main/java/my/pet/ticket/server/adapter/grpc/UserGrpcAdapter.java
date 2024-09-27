package my.pet.ticket.server.adapter.grpc;

import static my.pet.ticket.server.common.utils.GrpcMessageUtils.convertUserToUserResponse;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.List;
import my.pet.ticket.grpc.FilterRequest;
import my.pet.ticket.grpc.RegisterUserRequest;
import my.pet.ticket.grpc.UserResponse;
import my.pet.ticket.grpc.UserResponses;
import my.pet.ticket.grpc.UserServiceGrpc.UserServiceImplBase;
import my.pet.ticket.server.application.domain.model.User;
import my.pet.ticket.server.application.domain.service.DomainServiceException;
import my.pet.ticket.server.application.domain.service.UserService;
import my.pet.ticket.server.common.utils.GrpcMessageUtils;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.security.access.annotation.Secured;

@GrpcService
public class UserGrpcAdapter extends UserServiceImplBase {

  private static final DomainServiceException NULL_FILTER_OR_EMPTY_FIELD = new DomainServiceException(
      "Filter and they field user id shouldn't be empty");

  private final UserService userService;

  public UserGrpcAdapter(UserService userService) {
    this.userService = userService;
  }

  @Override
  public void registerUser(RegisterUserRequest request,
      StreamObserver<UserResponse> responseObserver) {
    User user = this.userService.register(
        builder -> builder.fullName(request.getFullName().getValue())
            .login(request.getLogin().getValue()).password(request.getPassword().getValue())
            .build());
    UserResponse response = convertUserToUserResponse(user);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  @Secured("ADMIN")
  public void activeUser(FilterRequest request, StreamObserver<UserResponse> responseObserver) {
    if (request.hasFilter() && request.getFilter().hasUserId()) {
      User user = this.userService.activateUser(request.getFilter().getUserId().getValue());
      UserResponse userResponse = convertUserToUserResponse(user);
      responseObserver.onNext(userResponse);
      responseObserver.onCompleted();
      return;
    }
    throw NULL_FILTER_OR_EMPTY_FIELD;
  }

  @Override
  @Secured("ADMIN")
  public void suspendUser(FilterRequest request, StreamObserver<UserResponse> responseObserver) {
    if (request.hasFilter() && request.getFilter().hasUserId()) {
      User user = this.userService.suspend(request.getFilter().getUserId().getValue());
      UserResponse userResponse = convertUserToUserResponse(user);
      responseObserver.onNext(userResponse);
      responseObserver.onCompleted();
      return;
    }
    throw NULL_FILTER_OR_EMPTY_FIELD;
  }

  @Override
  @Secured({"MANAGER", "ADMIN"})
  public void getUser(FilterRequest request, StreamObserver<UserResponse> responseObserver) {
    if (request.hasFilter() && request.getFilter().hasUserId()) {
      User user = this.userService.get(request.getFilter().getUserId().getValue());
      UserResponse userResponse = convertUserToUserResponse(user);
      responseObserver.onNext(userResponse);
      responseObserver.onCompleted();
      return;
    }
    throw NULL_FILTER_OR_EMPTY_FIELD;
  }

  @Override
  @Secured({"MANAGER", "ADMIN"})
  public void getAllUsers(FilterRequest request, StreamObserver<UserResponses> responseObserver) {
    List<User> users = new ArrayList<>();
    if (request.hasFilter()) {
      users.addAll(this.userService.getAll(request.getFilter().getPage().getValue(),
          request.getFilter().getPageSize().getValue()));
    } else {
      users.addAll(this.userService.getAll());
    }
    List<UserResponse> userResponses = users.stream().map(
        GrpcMessageUtils::convertUserToUserResponse).toList();
    responseObserver.onNext(UserResponses.newBuilder().addAllUsers(userResponses).build());
    responseObserver.onCompleted();
  }

  @Override
  @Secured("ADMIN")
  public void deleteUser(FilterRequest request, StreamObserver<Empty> responseObserver) {
    if (request.hasFilter() && request.getFilter().hasUserId()) {
      this.userService.delete(request.getFilter().getUserId().getValue());
      responseObserver.onNext(Empty.getDefaultInstance());
      responseObserver.onCompleted();
      return;
    }
    throw NULL_FILTER_OR_EMPTY_FIELD;
  }

}
