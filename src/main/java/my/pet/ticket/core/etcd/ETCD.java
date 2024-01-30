package my.pet.ticket.core.etcd;

import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KeyValue;
import io.etcd.jetcd.common.exception.EtcdException;
import io.etcd.jetcd.options.GetOption;
import my.pet.ticket.core.logging.Detail;
import my.pet.ticket.core.logging.Log;
import my.pet.ticket.core.logging.impl.DetailEtcdImpl;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

public class ETCD {
    private static Status STATUS = Status.OFF;
    private static long REQ_TIMEOUT;
    private static String DEFAULT_KEY;
    private static Client client;

    private static List<String> POSTFIXES;

    public static void init(EtcdProperty property) {
        REQ_TIMEOUT = property.getRequestTimeout();
        DEFAULT_KEY = property.getKeysPrefix() + property.getComponentName();
        POSTFIXES = List.of(
                "/value",
                "/value/"+ property.getDc(),
                "/value/"+ property.getDc() + "/" +property.getInstanceName()
        );
        client = Client.builder()
                .endpoints(property.getEndpoints())
                .keepaliveTime(Duration.of(property.getDialKeepAliveTime(), ChronoUnit.SECONDS))
                .keepaliveTimeout(Duration.of(property.getDialKeepAliveTimeout(), ChronoUnit.SECONDS))
                .connectTimeout(Duration.of(property.getDialTimeout(), ChronoUnit.SECONDS))
                .build();

        STATUS = Status.ON;
    }

    public static Status getStatus() {
        return STATUS;
    }

    public static <T> void readEtcd(Class<T> clazz, String etcdKey, T defaultValue, Consumer<T> setter)
            throws EtcdException {
        readEtcd(clazz, etcdKey, defaultValue, setter, true);
    }

    public static <T> void readEtcd(Class<T> clazz, String etcdKey, T defaultValue, Consumer<T> setter, Boolean isShow)
            throws EtcdException {

        try {
            List<KeyValue> kvs = getKVS(etcdKey);
            List<String> postfixes = new ArrayList<>(POSTFIXES);
            postfixes.reversed();

            HashMap<String, String> mapKeyToValue = new HashMap<>();
            for (KeyValue kv : kvs) {
                mapKeyToValue.put(kv.getKey().toString(), kv.getValue().toString());
            }

            for(String postfix: postfixes) {
                String key = DEFAULT_KEY + etcdKey  + postfix;

                if (mapKeyToValue.containsKey(key) && StringUtils.hasLength(mapKeyToValue.get(key))) {

                    String v = isShow ? mapKeyToValue.get(key): "*";
                    Detail detail = new DetailEtcdImpl("value", v);
                    Log.INFO("readETCD: ", detail);

                    setter.accept(clazz.cast(mapKeyToValue.get(key)));
                    return;
                }
            }

            if(defaultValue != null) {
                setter.accept(clazz.cast(defaultValue));
            }
        } catch (InterruptedException| ExecutionException| TimeoutException e) {
            Log.ERROR(e.getMessage());
        }
    }

//    public static <T> void readEtcd(Class<T> clazz, String etcdKey, T defaultValue, Consumer<T> setter, Boolean isShow,
//                                    WatchParams watchParams) throws EtcdException {
//       //TODO сделдаем потом
//    }

    public static boolean testKey(String etcdKey) {
        try {
            return !getKVS(etcdKey).isEmpty();

        } catch (InterruptedException| ExecutionException| TimeoutException e) {
            Log.ERROR(e.getMessage());
            return false;
        }
    }

    private static List<KeyValue> getKVS(String etcdKey)
            throws ExecutionException, InterruptedException, TimeoutException {

        return client.getKVClient().get(
                        ByteSequence.from(DEFAULT_KEY + etcdKey + POSTFIXES.getFirst(),
                                StandardCharsets.UTF_8),
                        GetOption.builder().isPrefix(true).build())
                .get(REQ_TIMEOUT, TimeUnit.SECONDS)
                .getKvs();
    }
}
