package my.pet.ticket.client.application.port.api;

import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import java.util.List;
import my.pet.ticket.client.application.domain.model.User;
import my.pet.ticket.grpc.Filter;
import my.pet.ticket.grpc.FilterRequest;

public interface UserGrpcPort {

  User getUser(Long userId, String token);

  default List<User> getAllUsers(String token) {
    return getAllUsers(FilterRequest.newBuilder().setEmpty(Empty.getDefaultInstance()).build(),
        token);
  }

  default List<User> getAllUsers(Integer page, Integer pageSize, String token) {
    return getAllUsers(FilterRequest.newBuilder().setFilter(
        Filter.newBuilder().setPage(Int32Value.of(page)).setPageSize(Int32Value.of(pageSize))
            .build()).build(), token);
  }

  List<User> getAllUsers(FilterRequest filterRequest, String token);

}
