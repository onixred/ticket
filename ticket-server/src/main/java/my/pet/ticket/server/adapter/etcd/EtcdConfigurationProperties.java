package my.pet.ticket.server.adapter.etcd;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.etcd")
public class EtcdConfigurationProperties {

  private Client client;

  @Data
  public static class Client {

    private String[] endpoints;

  }

}
