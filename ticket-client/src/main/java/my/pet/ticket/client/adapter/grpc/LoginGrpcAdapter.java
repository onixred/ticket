package my.pet.ticket.client.adapter.grpc;

import com.google.protobuf.StringValue;
import my.pet.ticket.client.application.domain.model.Token;
import my.pet.ticket.client.application.domain.model.payload.request.LoginRequest;
import my.pet.ticket.client.application.port.api.LoginGrpcPort;
import my.pet.ticket.grpc.LoginResponse;
import my.pet.ticket.grpc.LoginServiceGrpc.LoginServiceBlockingStub;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

@Component
public class LoginGrpcAdapter implements LoginGrpcPort {

  @GrpcClient("globalClient")
  private LoginServiceBlockingStub loginGrpcClient;

  @Override
  public Token login(LoginRequest request) {
    LoginResponse loginResponse = loginGrpcClient.login(
        (my.pet.ticket.grpc.LoginRequest.newBuilder()
            .setLogin(StringValue.of(request.getLogin()))
            .setPassword(StringValue.of(request.getPassword()))
            .build()));
    return Token.builder()
        .userId(loginResponse.getUserId().getValue())
        .token(loginResponse.getToken().getValue())
        .build();
  }

  public Boolean isTokenExpired(String token) {
    return this.loginGrpcClient.isExpired(StringValue.of(token))
        .getValue();
  }

}
