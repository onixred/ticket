package my.pet.ticket.client.application.port.api;

import my.pet.ticket.application.domain.model.Token;
import my.pet.ticket.application.domain.model.payload.request.LoginRequest;

public interface LoginGrpcPort {

  Token login(LoginRequest request);

}