package my.pet.ticket.client.adapter.grpc;

import static my.pet.utils.GrpcMessageUtils.convertUserResponseToUser;

import com.google.protobuf.Int64Value;
import java.util.List;
import my.pet.ticket.application.domain.model.User;
import my.pet.ticket.client.application.port.api.UserGrpcPort;
import my.pet.ticket.grpc.Filter;
import my.pet.ticket.grpc.FilterRequest;
import my.pet.ticket.grpc.UserResponse;
import my.pet.ticket.grpc.UserServiceGrpc.UserServiceBlockingStub;
import my.pet.utils.GrpcMessageUtils;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.client.security.CallCredentialsHelper;
import org.springframework.stereotype.Component;

@Component
public class UserGrpcAdapter implements UserGrpcPort {

  @GrpcClient("globalClient")
  private UserServiceBlockingStub userGrpcClient;

  @Override
  public User getUser(Long userId, String token) {
    UserResponse userResponse = this.userGrpcClient.withCallCredentials(
        CallCredentialsHelper.authorizationHeader(token)).getUser(FilterRequest.newBuilder()
        .setFilter(Filter.newBuilder().setUserId(Int64Value.of(userId)).build()).build());
    return convertUserResponseToUser(userResponse);
  }

  @Override
  public List<User> getAllUsers(FilterRequest filterRequest, String token) {
    return this.userGrpcClient.withCallCredentials(
            CallCredentialsHelper.authorizationHeader(token)).getAllUsers(filterRequest).getUsersList()
        .stream().map(GrpcMessageUtils::convertUserResponseToUser).toList();
  }

}
