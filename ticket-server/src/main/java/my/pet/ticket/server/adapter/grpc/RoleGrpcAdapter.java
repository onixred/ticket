package my.pet.ticket.server.adapter.grpc;

import static my.pet.utils.GrpcMessageUtils.convertRoleToRoleResponse;

import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import io.grpc.stub.StreamObserver;
import java.util.List;
import my.pet.ticket.application.domain.model.Role;
import my.pet.ticket.grpc.FilterRequest;
import my.pet.ticket.grpc.RoleResponse;
import my.pet.ticket.grpc.RoleResponses;
import my.pet.ticket.grpc.RoleServiceGrpc.RoleServiceImplBase;
import my.pet.ticket.server.application.domain.service.DomainServiceException;
import my.pet.ticket.server.application.domain.service.RoleService;
import my.pet.utils.GrpcMessageUtils;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.data.domain.Page;
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
  @Secured({"MANAGER", "ADMIN"})
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
  @Secured({"MANAGER", "ADMIN"})
  public void getAllRoles(FilterRequest request, StreamObserver<RoleResponses> responseObserver) {
    Page<Role> roles;
    if (request.hasFilter()) {
      roles = this.roleService.getAll(request.getFilter().getPage().getValue(),
          request.getFilter().getPageSize().getValue());
    } else {
      roles = this.roleService.getAll();
    }
    List<RoleResponse> roleResponses = roles.stream()
        .map(GrpcMessageUtils::convertRoleToRoleResponse).toList();
    responseObserver.onNext(RoleResponses.newBuilder().addAllRoles(roleResponses)
        .setPages(Int64Value.of(roles.getTotalPages()))
        .build());
    responseObserver.onCompleted();
  }

  @Override
  @Secured("ADMIN")
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
