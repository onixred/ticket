package my.pet.ticket.server.adapter.redis;

import java.util.Optional;
import my.pet.ticket.server.adapter.redis.entity.TokenEntity;
import my.pet.ticket.server.adapter.redis.repository.TokenRepository;
import my.pet.ticket.server.application.port.persistence.TokenPort;
import org.springframework.stereotype.Component;

@Component
public class TokenAdapter implements TokenPort {

  private final TokenRepository tokenRepository;

  public TokenAdapter(TokenRepository tokenRepository) {
    this.tokenRepository = tokenRepository;
  }

  @Override
  public Optional<TokenEntity> get(Long userId) {
    return this.tokenRepository.findById(userId);
  }

  @Override
  public Optional<TokenEntity> getByToken(String token) {
    return this.tokenRepository.findByToken(token);
  }

  @Override
  public TokenEntity create(Long userId, String token) {
    return get(userId).orElseGet(
        () -> this.tokenRepository.save(TokenEntity.builder().userId(userId).token(token).build()));
  }

  @Override
  public void delete(Long userId) {
    this.tokenRepository.deleteById(userId);
  }

}
