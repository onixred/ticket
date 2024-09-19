package my.pet.ticket.server.adapter.etcd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.etcd.jetcd.*;
import io.etcd.jetcd.kv.GetResponse;
import my.pet.ticket.server.application.port.configuration.ConfigurationPort;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

@Component
public class EtcdAdapter implements ConfigurationPort {

    private final ObjectMapper objectMapper;

    private final Client etcdClient;

    private final Auth authClient;

    private final KV keyValueClient;

    private final Cluster clusterClient;

    private final Maintenance maintenanceClient;

    private final Lease leaseClient;

    private final Watch watchClient;

    private final Lock lockClient;

    private final Election electionClient;

    public EtcdAdapter(ObjectMapper objectMapper, Client client) {
        this.objectMapper = objectMapper;
        this.etcdClient = client;
        this.authClient = client.getAuthClient();
        this.keyValueClient = client.getKVClient();
        this.clusterClient = client.getClusterClient();
        this.maintenanceClient = client.getMaintenanceClient();
        this.leaseClient = client.getLeaseClient();
        this.watchClient = client.getWatchClient();
        this.lockClient = client.getLockClient();
        this.electionClient = client.getElectionClient();
    }

    @PreDestroy
    public void preDestroy() {
        this.etcdClient.close();
    }

    @Override
    public <T> T put(Class<T> type, String key, T value, Consumer<T> consumer) {
        return null;
    }

    @Override
    public <T> T getFirst(Class<T> type, String key, T defaultValue, Consumer<T> consumer) {
        ByteSequence keyBs = ByteSequence.from(key.getBytes(StandardCharsets.UTF_8));
        CompletableFuture<GetResponse> completableFuture = this.keyValueClient.get(keyBs);
        try {
            GetResponse getResponse = completableFuture.get();
            List<KeyValue> keyValues = getResponse.getKvs();
            if (!keyValues.isEmpty()) {
                KeyValue keyValue = getResponse.getKvs().get(0);
                return convertJsonToObject(type, keyValue.getValue().toString());
            } else {
                return defaultValue;
            }
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T convertJsonToObject(Class<T> type, String value) {
        try {
            return this.objectMapper.readValue(value, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
