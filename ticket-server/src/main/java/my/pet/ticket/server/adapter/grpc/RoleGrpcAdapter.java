package my.pet.ticket.server.adapter.grpc;

import static my.pet.ticket.server.common.utils.GrpcMessageUtils.convertRoleToRoleResponse;

import com.google.protobuf.Empty;
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
import my.pet.ticket.server.common.utils.GrpcMessageUtils;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.security.access.annotation.Secured;

@GrpcService
public class RoleGrpcAdapter extends RoleServiceImplBase {

  private static final DomainServiceException NULL_FILTER_OR_EMPTY_FIELD = new DomainServiceException(
      "Filter and they field role id shouldn't be empty");

  private final RoleService roleService;

  public RoleGrpcAdapter(RoleService roleService) {
    this.roleService = roleService;
  }

  @Override
  @Secured("MANAGER")
  public void getRole(FilterRequest request, StreamObserver<RoleResponse> responseObserver) {
    if (request.hasFilter() && request.getFilter().hasRoleId()) {
      Role role = this.roleService.get(request.getFilter().getRoleId().getValue());
      RoleResponse response = convertRoleToRoleResponse(role);
      responseObserver.onNext(response);
      responseObserver.onCompleted();
      return;
    }
    throw NULL_FILTER_OR_EMPTY_FIELD;
  }

  @Override
  public void getAllRoles(FilterRequest request, StreamObserver<RoleResponses> responseObserver) {
    List<Role> users = new ArrayList<>();
    if (request.hasFilter()) {
      users.addAll(this.roleService.getAll(request.getFilter().getPage().getValue(),
          request.getFilter().getPageSize().getValue()));
    } else {
      users.addAll(this.roleService.getAll());
    }
    List<RoleResponse> roleResponses = users.stream()
        .map(GrpcMessageUtils::convertRoleToRoleResponse).toList();
    responseObserver.onNext(RoleResponses.newBuilder().addAllRoles(roleResponses).build());
    responseObserver.onCompleted();
  }

  @Override
  public void deleteRole(FilterRequest request, StreamObserver<Empty> responseObserver) {
    if (request.hasFilter() && request.getFilter().hasRoleId()) {
      this.roleService.delete(request.getFilter().getRoleId().getValue());
      responseObserver.onNext(Empty.getDefaultInstance());
      responseObserver.onCompleted();
      return;
    }
    throw NULL_FILTER_OR_EMPTY_FIELD;
  }

}
