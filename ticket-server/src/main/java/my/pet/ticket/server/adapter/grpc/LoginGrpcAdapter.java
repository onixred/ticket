package my.pet.ticket.server.adapter.grpc;

import com.google.protobuf.Int64Value;
import com.google.protobuf.StringValue;
import io.grpc.stub.StreamObserver;
import my.pet.ticket.grpc.LoginRequest;
import my.pet.ticket.grpc.LoginResponse;
import my.pet.ticket.grpc.LoginServiceGrpc.LoginServiceImplBase;
import my.pet.ticket.server.application.domain.model.Token;
import my.pet.ticket.server.application.domain.service.DomainServiceException;
import my.pet.ticket.server.application.domain.service.LoginService;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class LoginGrpcAdapter extends LoginServiceImplBase {

  private static final DomainServiceException LOGIN_OR_PASSWORD_IS_NOT_PRESENT = new DomainServiceException(
      "Login or password is not present in request");

  private final LoginService loginService;

  public LoginGrpcAdapter(LoginService loginService) {
    this.loginService = loginService;
  }

  @Override
  public void login(LoginRequest request, StreamObserver<LoginResponse> responseObserver) {
    if (request.hasLogin() && request.hasPassword()) {
      Token token = this.loginService.login(builder -> builder
          .login(request.getLogin().getValue())
          .password(request.getPassword().getValue())
          .build());
      responseObserver.onNext(LoginResponse.newBuilder()
          .setUserId(Int64Value.of(token.getUserId()))
          .setToken(StringValue.of(token.getToken()))
          .build());
      responseObserver.onCompleted();
      return;
    }
    throw LOGIN_OR_PASSWORD_IS_NOT_PRESENT;
  }

}
