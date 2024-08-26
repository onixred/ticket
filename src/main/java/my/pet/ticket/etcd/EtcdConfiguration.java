package my.pet.ticket.etcd;

import io.etcd.jetcd.Client;
import java.util.concurrent.ExecutionException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ETCD class for
 *
 * @author <a href="mailto:baranov.alexalex@gmail.com">abaranov</a>
 */
@Configuration
public class EtcdConfiguration {

    @Bean
    public EtcdClient etcdClient() throws ExecutionException, InterruptedException {
        String host = "http://localhost:2379";
        Client client = Client.builder().endpoints(host).build();

        return new EtcdClient(client);
    }
}
