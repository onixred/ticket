package my.pet.ticket.server.adapter.etcd;

import io.etcd.jetcd.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EtcdConfiguration {

    private final EtcdConfigurationProperties configurationProperties;

    public EtcdConfiguration(EtcdConfigurationProperties configurationProperties) {
        this.configurationProperties = configurationProperties;
    }

    @Bean
    public Client etcdClient() {
        return Client.builder().endpoints(this.configurationProperties.getClient().getEndpoints()).build();
    }

}
