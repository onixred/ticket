package my.pet.ticket.server.application.port.persistence;

import java.util.Optional;
import my.pet.ticket.server.adapter.redis.entity.TokenEntity;

public interface TokenPort {

  Optional<TokenEntity> get(Long userId);

  Optional<TokenEntity> getByToken(String token);

  TokenEntity create(Long userId, String token);

  void delete(Long userId);

}
