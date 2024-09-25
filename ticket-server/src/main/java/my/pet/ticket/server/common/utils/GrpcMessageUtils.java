package my.pet.ticket.server.common.utils;

import com.google.protobuf.BoolValue;
import com.google.protobuf.Int64Value;
import com.google.protobuf.StringValue;
import my.pet.ticket.grpc.ClientResponse;
import my.pet.ticket.grpc.RoleResponse;
import my.pet.ticket.grpc.TicketResponse;
import my.pet.ticket.grpc.TicketStatusResponse;
import my.pet.ticket.grpc.UserResponse;
import my.pet.ticket.server.application.domain.model.Client;
import my.pet.ticket.server.application.domain.model.Role;
import my.pet.ticket.server.application.domain.model.Ticket;
import my.pet.ticket.server.application.domain.model.TicketStatus;
import my.pet.ticket.server.application.domain.model.User;

public final class GrpcMessageUtils {

  public static UserResponse convertUserToUserResponse(User user) {
    return UserResponse.newBuilder().setUserId(Int64Value.of(user.getUserId()))
        .setRole(convertRoleToRoleResponse(user.getRole()))
        .setFullName(StringValue.of(user.getFullName())).setLogin(StringValue.of(user.getLogin()))
        .setActive(BoolValue.of(user.getActive())).setSuspended(BoolValue.of(user.getSuspended()))
        .build();
  }

  public static TicketStatusResponse convertTicketStatusToTicketStatusResponse(
      TicketStatus ticketStatus) {
    return TicketStatusResponse.newBuilder()
        .setTicketStatusId(Int64Value.of(ticketStatus.getTicketStatusId()))
        .setName(StringValue.of(ticketStatus.getName()))
        .build();
  }

  public static TicketResponse convertTicketToTicketResponse(Ticket ticket) {
    return TicketResponse.newBuilder()
        .setTicketId(Int64Value.of(ticket.getTicketId()))
        .setClient(convertClientToClientResponse(ticket.getClient()))
        .setAuthor(convertUserToUserResponse(ticket.getAuthor()))
        .setManager(convertUserToUserResponse(ticket.getManager()))
        .setTicketStatus(convertTicketStatusToTicketStatusResponse(ticket.getTicketStatus()))
        .setTitle(StringValue.of(ticket.getTitle()))
        .setDescription(StringValue.of(ticket.getDescription()))
        .build();
  }

  public static RoleResponse convertRoleToRoleResponse(Role role) {
    return RoleResponse.newBuilder()
        .setRoleId(Int64Value.of(role.getRoleId()))
        .setName(StringValue.of(role.getName()))
        .build();
  }

  public static ClientResponse convertClientToClientResponse(Client client) {
    return ClientResponse.newBuilder()
        .setClientId(Int64Value.of(client.getClientId()))
        .setFullName(StringValue.of(client.getFullName()))
        .setEmail(StringValue.of(client.getEmail()))
        .setPhoneNumber(StringValue.of(client.getPhoneNumber()))
        .build();
  }

}
