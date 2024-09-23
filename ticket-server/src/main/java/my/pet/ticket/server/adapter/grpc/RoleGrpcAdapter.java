package my.pet.ticket.server.adapter.grpc;

import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import com.google.protobuf.StringValue;
import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.List;
import my.pet.ticket.grpc.FilterRequest;
import my.pet.ticket.grpc.RoleResponse;
import my.pet.ticket.grpc.RoleResponses;
import my.pet.ticket.grpc.RoleServiceGrpc.RoleServiceImplBase;
import my.pet.ticket.server.application.domain.model.Role;
import my.pet.ticket.server.application.domain.service.DomainServiceException;
import my.pet.ticket.server.application.domain.service.RoleService;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class RoleGrpcAdapter extends RoleServiceImplBase {

  private static final DomainServiceException NULL_FILTER_OR_EMPTY_FIELD = new DomainServiceException(
      "Filter and they field user id shouldn't be empty");

  private final RoleService roleService;

  public RoleGrpcAdapter(RoleService roleService) {
    this.roleService = roleService;
  }

  @Override
  public void getRole(FilterRequest request, StreamObserver<RoleResponse> responseObserver) {
    if (request.hasFilter() && request.getFilter().hasRoleId()) {
      Role role = this.roleService.getRole(request.getFilter().getRoleId().getValue());
      RoleResponse response = convertRoleToRoleResponse(role);
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
    throw NULL_FILTER_OR_EMPTY_FIELD;
  }

  @Override
  public void getAllRoles(FilterRequest request, StreamObserver<RoleResponses> responseObserver) {
    List<Role> users = new ArrayList<>();
    if (request.hasFilter()) {
      //users.addAll(this.roleService.getAllRoles(request.getFilter().getPage().getValue(),
      //    request.getFilter().getPageSize().getValue()));
    } else {
      users.addAll(this.roleService.getAllRoles());
    }
    List<RoleResponse> roleResponses = users.stream().map(this::convertRoleToRoleResponse).toList();
    responseObserver.onNext(RoleResponses.newBuilder().addAllRoles(roleResponses).build());
    responseObserver.onCompleted();
  }

  @Override
  public void deleteRole(FilterRequest request, StreamObserver<Empty> responseObserver) {
    if (request.hasFilter() && request.getFilter().hasRoleId()) {
      this.roleService.deleteRole(request.getFilter().getRoleId().getValue());
      responseObserver.onNext(Empty.getDefaultInstance());
      responseObserver.onCompleted();
    }
    throw NULL_FILTER_OR_EMPTY_FIELD;
  }

  private RoleResponse convertRoleToRoleResponse(Role role) {
    return RoleResponse.newBuilder()
        .setRoleId(Int64Value.of(role.getRoleId()))
        .setName(StringValue.of(role.getName()))
        .build();
  }

}
