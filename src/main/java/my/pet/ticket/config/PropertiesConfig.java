package my.pet.ticket.config;

import ch.qos.logback.classic.Logger;
import io.etcd.jetcd.*;
import io.etcd.jetcd.kv.GetResponse;
import io.etcd.jetcd.watch.WatchEvent;
import io.etcd.jetcd.watch.WatchResponse;
import my.pet.ticket.config.dto.AppProperties;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Configuration
public class PropertiesConfig {

    private static Logger logger = (Logger) LoggerFactory.getLogger(PropertiesConfig.class);

    @Bean
    public AppProperties initProperties() {
        logger.info("start init properties");
        try (Client client = Client.builder().endpoints("http://127.0.0.1:2380").build();) {
            KV kv = client.getKVClient();

            AppProperties appProperties = new AppProperties();

            Field[] fields = appProperties.getClass().getDeclaredFields();
            Watch watch = client.getWatchClient();

            for (Field field : fields) {
                ByteSequence byteSequence = ByteSequence.from(field.getName().getBytes());
                CompletableFuture<GetResponse> completableFuture = kv.get(byteSequence);
                GetResponse getResponse = completableFuture.get();
                List<KeyValue> keyValues = getResponse.getKvs();

                Watch.Listener listener = new Watch.Listener() {
                    @Override
                    public void onNext(WatchResponse response) {
                        logger.info("info listener {}", response);
                        List<WatchEvent> watchEvents = response.getEvents();
                        watchEvents.forEach(watchEvent -> {
                            if (watchEvent.getKeyValue().getKey().toString().equals(field.getName())) {
                                appProperties.setDatabaseUrl(watchEvent.getKeyValue().getValue().toString());
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        logger.info("field {} onError {}", field.getName(), throwable.toString());
                    }

                    @Override
                    public void onCompleted() {
                        logger.info("onCompleted listener {}", field.getName());
                    }
                };
                watch.watch(byteSequence, listener);

                keyValues.forEach(keyValue -> {
                    if (keyValue.getKey().toString().equals(field.getName())) {
                        logger.info("{}: {}", field.getName(), keyValue.getValue().toString());

                        try {
                            Field fieldForSet = appProperties.getClass().getDeclaredField(field.getName());
                            fieldForSet.setAccessible(true);
                            fieldForSet.set(appProperties, keyValue.getValue().toString());
                        } catch (IllegalAccessException | NoSuchFieldException e) {
                            logger.info("{}", e.getMessage());
                            throw new RuntimeException(e);
                        }
                        appProperties.setDatabaseUrl(keyValue.getValue().toString());
                    }
                });
            }
            return appProperties;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("настройки не поднялись");

    }
}
