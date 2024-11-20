package my.pet.ticket.infrastructure.config;

import my.pet.ticket.infrastructure.etcd.EtcdProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EtcdPropertyConfig {

    @Value("${etcd.endpoints}")
    private String[] endpoints;

    @Bean
    EtcdProperty etcdProperty() {
        return EtcdProperty.builder()
                .endpoints(endpoints)
                .build();
    }

}
