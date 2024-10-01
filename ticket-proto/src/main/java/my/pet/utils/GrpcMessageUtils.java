package my.pet.utils;

import com.google.protobuf.BoolValue;
import com.google.protobuf.Int64Value;
import com.google.protobuf.StringValue;
import my.pet.ticket.application.domain.model.Client;
import my.pet.ticket.application.domain.model.Role;
import my.pet.ticket.application.domain.model.Ticket;
import my.pet.ticket.application.domain.model.TicketStatus;
import my.pet.ticket.application.domain.model.User;
import my.pet.ticket.grpc.ClientResponse;
import my.pet.ticket.grpc.RoleResponse;
import my.pet.ticket.grpc.TicketResponse;
import my.pet.ticket.grpc.TicketStatusResponse;
import my.pet.ticket.grpc.UserResponse;

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

  public static User convertUserResponseToUser(UserResponse userResponse) {
    return User.builder().userId(userResponse.getUserId().getValue())
        .role(convertRoleResponseToRole(userResponse.getRole()))
        .login(userResponse.getLogin().getValue()).fullName(userResponse.getFullName().getValue())
        .active(userResponse.getActive().getValue())
        .suspended(userResponse.getSuspended().getValue()).build();
  }

  public static TicketStatus convertTicketStatusResponseToTicketStatus(
      TicketStatusResponse ticketStatusResponse) {
    return TicketStatus.builder()
        .ticketStatusId(ticketStatusResponse.getTicketStatusId().getValue())
        .name(ticketStatusResponse.getName().getValue())
        .build();
  }

  public static Ticket convertTicketResponseToTicket(TicketResponse ticketResponse) {
    return Ticket.builder()
        .ticketId(ticketResponse.getTicketId().getValue())
        .client(convertClientResponseToClient(ticketResponse.getClient()))
        .author(convertUserResponseToUser(ticketResponse.getAuthor()))
        .manager(convertUserResponseToUser(ticketResponse.getManager()))
        .ticketStatus(convertTicketStatusResponseToTicketStatus(ticketResponse.getTicketStatus()))
        .title(ticketResponse.getTitle().getValue())
        .description(ticketResponse.getDescription().getValue())
        .build();
  }

  public static Role convertRoleResponseToRole(RoleResponse roleResponse) {
    return Role.builder().roleId(roleResponse.getRoleId().getValue())
        .name(roleResponse.getName().getValue()).build();
  }

  public static Client convertClientResponseToClient(ClientResponse clientResponse) {
    return Client.builder()
        .clientId(clientResponse.getClientId().getValue())
        .fullName(clientResponse.getFullName().getValue())
        .email(clientResponse.getEmail().getValue())
        .phoneNumber(clientResponse.getPhoneNumber().getValue())
        .build();
  }

}
