package my.pet.ticket.core.etcd;

import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KeyValue;
import io.etcd.jetcd.common.exception.EtcdException;
import io.etcd.jetcd.options.GetOption;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

public class ETCD {
    private static Status STATUS = Status.OFF;
    private static long REQ_TIMEOUT;
    private static String DEFAULT_KEY;
    private static Client client;

    private static List<String> POSTFIXS;

    public static void init(EtcdProperty property) {
        REQ_TIMEOUT = Long.parseLong(property.requestTimeout());
        DEFAULT_KEY = property.keysPrefix() + property.componentName();
        POSTFIXS = List.of("/value", "/value/"+ property.dc(),  "/value/"+ property.dc() + "/" +property.instanceName());
        client = Client.builder()
                .endpoints(property.endpoints())
                .keepaliveTime(Duration.parse(property.dialKeepAliveTime()))
                .keepaliveTimeout(Duration.parse(property.dialKeepAliveTimeout()))
                .connectTimeout(Duration.parse(property.dialTimeout()))
                .build();

        STATUS = Status.ON;
    }

    public static Status getStatus() {
        return STATUS;
    }
    public static <T> void readEtcd(Class<T> clazz, String etcdKey, T defaultValue, Consumer<T> setter)
            throws EtcdException {
        try {
            List<KeyValue> kvs = getKVS(etcdKey);
            List<String> postfixes = new ArrayList<>(POSTFIXS);
            postfixes.reversed();

            Map<String, String> mapKeyToValue = new HashMap<>();
            for (KeyValue kv : kvs) {
                mapKeyToValue.put(kv.getKey().toString(), kv.getValue().toString());
            }

            for(String postfix: postfixes) {
                String key = DEFAULT_KEY + etcdKey  + postfix;
                if (mapKeyToValue.containsKey(key) && StringUtils.hasLength(mapKeyToValue.get(key))) {
                    setter.accept(clazz.cast(mapKeyToValue.get(key)));
                    return;
                }
            }

            if(defaultValue != null) {
                setter.accept(clazz.cast(defaultValue));
            }
        } catch (InterruptedException| ExecutionException| TimeoutException e) {
            System.err.println(e.getMessage());
        }


    }

    public static <T> void readEtcd(Class<T> clazz, String etcdKey, T defaultValue, Consumer<T> setter, Boolean isShow) throws EtcdException {

    }

    public static <T> void readEtcd(Class<T> clazz, String etcdKey, T defaultValue, Consumer<T> setter, Boolean isShow, WatchParams watchParams) throws EtcdException {
       //TODO сделдаем потом
    }

    public static boolean testKey(String etcdKey) {
        try {
            List<KeyValue> kvs = getKVS(etcdKey);
            return kvs.stream()
                    .map(kv -> kv.getValue().toString(StandardCharsets.UTF_8))
                    .anyMatch(StringUtils::hasLength);

        } catch (InterruptedException| ExecutionException| TimeoutException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    private static List<KeyValue> getKVS (String etcdKey)
            throws ExecutionException, InterruptedException, TimeoutException {
        return client.getKVClient().get(
                        ByteSequence.from(DEFAULT_KEY + etcdKey + POSTFIXS.getFirst(),
                                StandardCharsets.UTF_8),
                        GetOption.builder().isPrefix(true).build())
                .get(REQ_TIMEOUT, TimeUnit.SECONDS)
                .getKvs();
    }
}
