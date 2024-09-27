package my.pet.ticket.client.adapter.grpc;

import com.google.protobuf.Int64Value;
import java.util.List;
import my.pet.ticket.client.application.domain.model.Role;
import my.pet.ticket.client.application.domain.model.User;
import my.pet.ticket.client.application.port.api.UserGrpcPort;
import my.pet.ticket.grpc.Filter;
import my.pet.ticket.grpc.FilterRequest;
import my.pet.ticket.grpc.RoleResponse;
import my.pet.ticket.grpc.UserResponse;
import my.pet.ticket.grpc.UserServiceGrpc.UserServiceBlockingStub;
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
    RoleResponse roleResponse = userResponse.getRole();
    return User.builder().userId(userResponse.getUserId().getValue()).role(
            Role.builder().roleId(roleResponse.getRoleId().getValue())
                .name(roleResponse.getName().getValue()).build())
        .login(userResponse.getLogin().getValue()).fullName(userResponse.getFullName().getValue())
        .active(userResponse.getActive().getValue())
        .suspended(userResponse.getSuspended().getValue()).build();
  }

  @Override
  public List<User> getAllUsers(FilterRequest filterRequest, String token) {
    return List.of();
  }

}
