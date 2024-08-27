package my.pet.ticket.etcd;

import io.etcd.jetcd.Client;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * ETCD class for
 *
 * @author <a href="mailto:baranov.alexalex@gmail.com">abaranov</a>
 */
@Configuration
@PropertySource("classpath:application.properties")
public class EtcdConfiguration {

    @Value("${etcd.host}")
    private String host;

    @Bean
    public EtcdClient etcdClient() {
        Client client = Client.builder().endpoints(host).build();

        return new EtcdClient(client);
    }
}
