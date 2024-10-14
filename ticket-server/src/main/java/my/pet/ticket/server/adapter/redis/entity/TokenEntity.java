package my.pet.ticket.server.adapter.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Data
@RedisHash(timeToLive = 3600)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenEntity {

  @Id
  private Long userId;

  @Indexed
  private String token;

}
