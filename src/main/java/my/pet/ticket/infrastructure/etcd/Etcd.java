package my.pet.ticket.infrastructure.etcd;

import io.etcd.jetcd.*;
import io.etcd.jetcd.common.exception.EtcdException;
import io.etcd.jetcd.kv.GetResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import my.pet.ticket.infrastructure.etcd.exception.EtcdClientException;
import my.pet.ticket.infrastructure.logging.Log;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Etcd {

    private static Client client;
    private static KV kvClient;

    public static void init(EtcdProperty etcdProperty) {
        if (client != null) {
            return;
        }

        client = Client.builder()
                .endpoints(etcdProperty.getEndpoints())
                .build();

        kvClient = client.getKVClient();
    }

    public static boolean isInitialized() {
        return client != null;
    }

    public static <T> void readEtcd(Class<T> clazz, String etcdKey, T defaultValue, Consumer<T> setter) throws EtcdException {
        readEtcd(clazz, etcdKey, defaultValue, setter, null);
    }

    public static <T> void readEtcd(Class<T> clazz, String etcdKey, T defaultValue, Consumer<T> setter, Boolean isShow) throws EtcdException {
        if (kvClient == null) {
            return;
        }

        try {
            ByteSequence key = ByteSequence.from(etcdKey, StandardCharsets.UTF_8);
            CompletableFuture<GetResponse> completableFuture = kvClient.get(key);
            GetResponse getResponse = completableFuture.get();
            List<KeyValue> keyValues = getResponse.getKvs();

            if (keyValues.isEmpty()) {
                setter.accept(defaultValue);
                return;
            }

            for (KeyValue keyValue : keyValues) {
                T value = parse(keyValue.getValue(), clazz).orElse(defaultValue);
                Log.INFO(String.format("Etcd get {'%s': '%s'}", etcdKey, Boolean.TRUE.equals(isShow) ? value : "hidden"));
                setter.accept(value);
            }
        } catch (InterruptedException | ExecutionException exception) {
            throw new EtcdClientException("Failed to fetch ETCD data", exception);
        }
    }

    public static boolean testKey(String etcdKey) {
        if (kvClient == null) {
            return false;
        }

        return kvClient.get(ByteSequence.from(etcdKey, StandardCharsets.UTF_8))
                .thenApply(GetResponse::getKvs)
                .thenApply(List::isEmpty)
                .exceptionally(exception -> false)
                .join();
    }

    private static <T> Optional<T> parse(ByteSequence value, Class<T> clazz) {
        try {
            if (clazz == String.class) {
                return Optional.ofNullable(clazz.cast(value.toString()));
            }
        } catch (ClassCastException ignored) {
        }

        return Optional.empty();
    }

}
