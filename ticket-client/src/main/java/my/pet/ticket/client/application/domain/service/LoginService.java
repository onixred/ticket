package my.pet.ticket.client.application.domain.service;

import com.google.protobuf.StringValue;
import io.grpc.StatusRuntimeException;
import my.pet.ticket.client.adapter.http.exception.LoginAdapterException;
import my.pet.ticket.client.application.domain.model.Token;
import my.pet.ticket.client.application.domain.model.payload.request.LoginRequest;
import my.pet.ticket.grpc.LoginResponse;
import my.pet.ticket.grpc.LoginServiceGrpc.LoginServiceBlockingStub;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

  @GrpcClient("globalClient")
  private LoginServiceBlockingStub loginGrpcClient;

  public Token login(LoginRequest request) {
    try {
      LoginResponse loginResponse = loginGrpcClient.login(
          (my.pet.ticket.grpc.LoginRequest.newBuilder()
              .setLogin(StringValue.of(request.getLogin()))
              .setPassword(StringValue.of(request.getPassword()))
              .build()));
      return Token.builder()
          .userId(loginResponse.getUserId().getValue())
          .token(loginResponse.getToken().getValue())
          .build();
    } catch (StatusRuntimeException exception) {
      throw new LoginAdapterException(exception);
    }
  }

}
