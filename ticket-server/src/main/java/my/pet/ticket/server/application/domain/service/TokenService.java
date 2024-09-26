package my.pet.ticket.server.application.domain.service;

import java.util.UUID;
import my.pet.ticket.server.adapter.redis.TokenAdapter;
import my.pet.ticket.server.adapter.redis.entity.TokenEntity;
import my.pet.ticket.server.application.domain.model.Token;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

  private static final DomainServiceException TOKEN_NOT_FOUND = new DomainServiceException(
      "Token not found");

  private final TokenAdapter tokenAdapter;

  private final ModelMapper modelMapper;

  public TokenService(TokenAdapter tokenAdapter, ModelMapper modelMapper) {
    this.tokenAdapter = tokenAdapter;
    this.modelMapper = modelMapper;
  }

  public Token getToken(Long userId) {
    TokenEntity tokenEntity = this.tokenAdapter.get(userId).orElseThrow(() -> TOKEN_NOT_FOUND);
    return modelMapper.map(tokenEntity, Token.class);
  }

  public Token getTokenByToken(String token) {
    TokenEntity tokenEntity = this.tokenAdapter.getByToken(token)
        .orElseThrow(() -> TOKEN_NOT_FOUND);
    return modelMapper.map(tokenEntity, Token.class);
  }

  public Token createToken(Long userId) {
    TokenEntity tokenEntity = this.tokenAdapter.create(userId,
        UUID.nameUUIDFromBytes(String.valueOf(userId).getBytes()).toString());
    return modelMapper.map(tokenEntity, Token.class);
  }

  public void deleteToken(Long userId) {
    this.tokenAdapter.delete(userId);
  }

}
