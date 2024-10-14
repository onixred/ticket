package my.pet.ticket.server.common.cache;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.Duration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

@Configuration
public class RedisCacheConfiguration {

  @Bean
  public org.springframework.data.redis.cache.RedisCacheConfiguration cacheConfiguration() {
    GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
    genericJackson2JsonRedisSerializer.configure(objectMapper -> {
      objectMapper.registerModule(new JavaTimeModule());
    });
    return org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig()
        .entryTtl(Duration.ofMinutes(60))
        .disableCachingNullValues()
        .serializeValuesWith(SerializationPair.fromSerializer(genericJackson2JsonRedisSerializer));
  }

}
