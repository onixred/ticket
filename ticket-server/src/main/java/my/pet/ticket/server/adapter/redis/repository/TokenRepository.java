package my.pet.ticket.server.adapter.redis.repository;

import java.util.Optional;
import my.pet.ticket.server.adapter.redis.entity.TokenEntity;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends KeyValueRepository<TokenEntity, Long> {

  Optional<TokenEntity> findByToken(String token);

}
