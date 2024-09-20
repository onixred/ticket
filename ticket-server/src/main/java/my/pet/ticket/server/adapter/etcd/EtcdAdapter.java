package my.pet.ticket.server.adapter.etcd;

import io.etcd.jetcd.Auth;
import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.Cluster;
import io.etcd.jetcd.Election;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.KeyValue;
import io.etcd.jetcd.Lease;
import io.etcd.jetcd.Lock;
import io.etcd.jetcd.Maintenance;
import io.etcd.jetcd.Watch;
import io.etcd.jetcd.kv.GetResponse;
import io.etcd.jetcd.options.GetOption;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import javax.annotation.PreDestroy;
import my.pet.ticket.server.application.port.configuration.ConfigurationPort;

public class EtcdAdapter
        implements ConfigurationPort {

    private final Client etcdClient;

    private final Auth authClient;

    private final KV keyValueClient;

    private final Cluster clusterClient;

    private final Maintenance maintenanceClient;

    private final Lease leaseClient;

    private final Watch watchClient;

    private final Lock lockClient;

    private final Election electionClient;

    public EtcdAdapter(Client client) {
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
    public String put(String key, String value) {
        return "";
    }

    @Override
    public String getFirst(String key) {
        ByteSequence keyBs = ByteSequence.from(key.getBytes(StandardCharsets.UTF_8));
        CompletableFuture<GetResponse> completableFuture = this.keyValueClient.get(keyBs);
        try {
            GetResponse getResponse = completableFuture.get();
            List<KeyValue> keyValues = getResponse.getKvs();
            if (!keyValues.isEmpty()) {
                KeyValue keyValue = getResponse.getKvs().get(0);
                return keyValue.getValue().toString();
            }
            return "";
        } catch (ExecutionException e) {
            throw new EtcdAdapterException("Something went wrong", e);
        } catch (InterruptedException e) {
            throw new EtcdAdapterException("Thread is interrupted", e);
        }
    }

    @Override
    public Map<String, String> getAll(String key) {
        ByteSequence keyBs = ByteSequence.from(key.getBytes(StandardCharsets.UTF_8));
        CompletableFuture<GetResponse> completableFuture = this.keyValueClient.get(keyBs,
            GetOption.builder().isPrefix(true).build());
        try {
            GetResponse getResponse = completableFuture.get();
            List<KeyValue> keyValues = getResponse.getKvs();
            Map<String, String> keyValuesMap = new HashMap<>();
            if (!keyValues.isEmpty()) {
                for (KeyValue keyValue : keyValues) {
                    keyValuesMap.put(keyValue.getKey().toString().replace(key, ""),
                        keyValue.getValue().toString());
                }
            }
            return keyValuesMap;
        } catch (ExecutionException e) {
            throw new EtcdAdapterException("Something went wrong", e);
        } catch (InterruptedException e) {
            throw new EtcdAdapterException("Thread is interrupted", e);
        }
    }

    @Override
    public Map<String, String> getAllByProfile(String profile) {
        return getAll("configs." + profile + ".data.");
    }

}
