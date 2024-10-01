package my.pet.ticket.client.adapter.grpc;

import static my.pet.utils.GrpcMessageUtils.convertUserResponseToUser;

import com.google.protobuf.Int64Value;
import com.google.protobuf.StringValue;
import java.security.MessageDigest;
import java.util.List;
import my.pet.ticket.application.domain.model.User;
import my.pet.ticket.application.domain.model.payload.request.RegisterUserRequest;
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

  private final MessageDigest md5Digest;

  public UserGrpcAdapter(MessageDigest md5Digest) {
    this.md5Digest = md5Digest;
  }

  @Override
  public User registerUser(RegisterUserRequest request) {
    UserResponse userResponse = this.userGrpcClient.registerUser(
        my.pet.ticket.grpc.RegisterUserRequest.newBuilder()
            .setFullName(StringValue.of(request.getFullName()))
            .setLogin(StringValue.of(request.getLogin()))
            .setPassword(
                StringValue.of(new String(md5Digest.digest(request.getPassword().getBytes()))))
            .build());
    return convertUserResponseToUser(userResponse);
  }

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