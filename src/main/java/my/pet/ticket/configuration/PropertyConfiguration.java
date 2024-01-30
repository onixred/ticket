package my.pet.ticket.configuration;

import my.pet.ticket.core.etcd.EtcdProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertyConfiguration {
    @Bean
    @ConfigurationProperties("etcd")
    public EtcdProperty etcdProperty(){
      return  new EtcdProperty();
    }
}
