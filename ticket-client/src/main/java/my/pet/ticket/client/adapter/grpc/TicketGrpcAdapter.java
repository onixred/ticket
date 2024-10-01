package my.pet.ticket.client.adapter.grpc;

import com.google.protobuf.Empty;
import java.util.List;
import my.pet.ticket.client.application.domain.model.Client;
import my.pet.ticket.client.application.domain.model.Role;
import my.pet.ticket.client.application.domain.model.Ticket;
import my.pet.ticket.client.application.domain.model.TicketStatus;
import my.pet.ticket.client.application.domain.model.User;
import my.pet.ticket.grpc.FilterRequest;
import my.pet.ticket.grpc.TicketResponse;
import my.pet.ticket.grpc.TicketServiceGrpc.TicketServiceBlockingStub;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.client.security.CallCredentialsHelper;
import org.springframework.stereotype.Component;

@Component
public class TicketGrpcAdapter {

  @GrpcClient("globalClient")
  private TicketServiceBlockingStub ticketServiceBlockingStub;

  public List<Ticket> getAllTickets(String token) {
    return this.ticketServiceBlockingStub
        .withCallCredentials(CallCredentialsHelper.authorizationHeader(token))
        .getAllTickets(FilterRequest.newBuilder().setEmpty(Empty.getDefaultInstance()).build())
        .getTicketsList()
        .stream()
        .map(this::convertTicketResponseToTicket)
        .toList();

  }

  private Ticket convertTicketResponseToTicket(TicketResponse ticketResponse) {
    return Ticket.builder()
        .ticketId(ticketResponse.getTicketId().getValue())
        .client(Client.builder()
            .clientId(ticketResponse.getClient().getClientId().getValue())
            .fullName(ticketResponse.getClient().getFullName().getValue())
            .email(ticketResponse.getClient().getEmail().getValue())
            .phoneNumber(ticketResponse.getClient().getPhoneNumber().getValue())
            .build())
        .author(User.builder()
            .userId(ticketResponse.getAuthor().getUserId().getValue())
            .role(Role.builder()
                .roleId(ticketResponse.getAuthor().getRole().getRoleId().getValue())
                .name(ticketResponse.getAuthor().getRole().getName().getValue())
                .build())
            .login(ticketResponse.getAuthor().getLogin().getValue())
            .fullName(ticketResponse.getAuthor().getFullName().getValue())
            .active(ticketResponse.getAuthor().getActive().getValue())
            .suspended(ticketResponse.getAuthor().getSuspended().getValue())
            .build())
        .manager(User.builder()
            .userId(ticketResponse.getManager().getUserId().getValue())
            .role(Role.builder()
                .roleId(ticketResponse.getManager().getRole().getRoleId().getValue())
                .name(ticketResponse.getManager().getRole().getName().getValue())
                .build())
            .login(ticketResponse.getManager().getLogin().getValue())
            .fullName(ticketResponse.getManager().getFullName().getValue())
            .active(ticketResponse.getManager().getActive().getValue())
            .suspended(ticketResponse.getManager().getSuspended().getValue())
            .build())
        .ticketStatus(TicketStatus.builder()
            .ticketStatusId(ticketResponse.getTicketStatus().getTicketStatusId().getValue())
            .name(ticketResponse.getTicketStatus().getName().getValue())
            .build())
        .title(ticketResponse.getTitle().getValue())
        .description(ticketResponse.getDescription().getValue())
        .build();
  }

}
