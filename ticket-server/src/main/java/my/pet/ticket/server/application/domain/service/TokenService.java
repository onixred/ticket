package my.pet.ticket.server.application.domain.service;

import java.time.LocalDateTime;
import java.util.UUID;
import my.pet.ticket.application.domain.model.Token;
import my.pet.ticket.server.adapter.redis.TokenAdapter;
import my.pet.ticket.server.adapter.redis.entity.TokenEntity;
import my.pet.ticket.server.application.port.persistence.TokenPort;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

  private static final DomainServiceException TOKEN_NOT_FOUND = new DomainServiceException(
      "Token not found");

  private final TokenPort tokenPort;

  private final ModelMapper modelMapper;

  public TokenService(TokenAdapter tokenPort, ModelMapper modelMapper) {
    this.tokenPort = tokenPort;
    this.modelMapper = modelMapper;
  }

  public Token getToken(Long userId) {
    TokenEntity tokenEntity = this.tokenPort.get(userId).orElseThrow(() -> TOKEN_NOT_FOUND);
    return modelMapper.map(tokenEntity, Token.class);
  }

  public Token getTokenByToken(String token) {
    TokenEntity tokenEntity = this.tokenPort.getByToken(token)
        .orElseThrow(() -> TOKEN_NOT_FOUND);
    return modelMapper.map(tokenEntity, Token.class);
  }

  public Token createToken(Long userId) {
    TokenEntity tokenEntity = this.tokenPort.create(userId,
        UUID.nameUUIDFromBytes(String.valueOf(userId + LocalDateTime.now().toString()).getBytes())
            .toString());
    return modelMapper.map(tokenEntity, Token.class);
  }

  public Boolean isTokenExist(String token) {
    return this.tokenPort.isExist(token);
  }

  public void deleteToken(Long userId) {
    this.tokenPort.delete(userId);
  }

}
