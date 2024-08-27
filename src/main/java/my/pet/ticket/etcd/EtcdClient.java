package my.pet.ticket.etcd;

import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.KeyValue;
import io.etcd.jetcd.common.exception.EtcdException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.pet.ticket.etcd.exception.PairNotFoundException;
import org.apache.commons.lang3.tuple.Pair;

/**
 * ETCDClient class for reading ETCD data
 *
 * @author <a href="mailto:baranov.alexalex@gmail.com">abaranov</a>
 */
@Slf4j
@RequiredArgsConstructor
public class EtcdClient {

    private final Client client;

    public <T> void readEtcd(Class<T> clazz, String etcdKey, T defaultValue, Consumer<T> setter) throws EtcdException {
        readEtcd(clazz, etcdKey, defaultValue, setter, true);
    }

    public <T> void readEtcd(Class<T> clazz, String etcdKey, T defaultValue, Consumer<T> setter, Boolean isShow) throws EtcdException {
        KV kvClient = client.getKVClient();
        ByteSequence currentKey = ByteSequence.from(etcdKey.getBytes());
        try {

            List<KeyValue> kvs = kvClient.get(currentKey).get().getKvs();

            Optional<Pair<String, String>> kvOpt = kvs.stream().findFirst().map(k->Pair.of(k.getKey().toString(), k.getValue().toString()) );

            if (kvOpt.isEmpty()) {
                if(defaultValue != null) {
                    setter.accept(defaultValue);
                } else {
                    throw new PairNotFoundException("Пары с ключом " + currentKey + " не найдено");
                }
            }

            Pair<String, String> kv = kvOpt.get();
            String key = kv.getKey();
            String value = kv.getValue();

            if(!isShow) {
                value= "***";
            }

            log.debug("ключ " + key + " значение " + value);

            setter.accept(clazz.cast(value));

        } catch (InterruptedException | ExecutionException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
