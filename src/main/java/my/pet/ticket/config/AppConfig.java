package my.pet.ticket.config;

import ch.qos.logback.classic.Logger;
import io.etcd.jetcd.*;
import io.etcd.jetcd.kv.GetResponse;
import io.etcd.jetcd.watch.WatchEvent;
import io.etcd.jetcd.watch.WatchResponse;
import my.pet.ticket.config.dto.AppProperties;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Configuration
public class AppConfig {

    @Value("${etcd.url}")
    private String etcdUrl;

    private static Logger logger = (Logger) LoggerFactory.getLogger(AppConfig.class);

    @Bean
    public AppProperties initProperties() {
        logger.info("start init properties");
        try {
            //Если закрываем клиента то listener завершают свою работу
            Client client = Client.builder().endpoints(etcdUrl).build();
            KV kv = client.getKVClient();

            AppProperties appProperties = new AppProperties();

            Field[] fieldsApp = appProperties.getClass().getDeclaredFields();

            for (Field field : fieldsApp) {
                ByteSequence byteSequence = ByteSequence.from(field.getName().getBytes());
                CompletableFuture<GetResponse> completableFuture = kv.get(byteSequence);
                GetResponse getResponse = completableFuture.get();
                List<KeyValue> keyValues = getResponse.getKvs();
                keyValues.forEach(keyValue -> {
                    logger.info("{}: {}", field.getName(), keyValue.getValue().toString());
                    setFieldForProperties(field, keyValue.getValue().toString(), appProperties);
                });
            }

            for (Field field : fieldsApp) {
                ByteSequence byteSequence = ByteSequence.from(field.getName().getBytes());

                Watch watch = client.getWatchClient();
                Watch.Watcher watcher = watch.watch(byteSequence, new Watch.Listener() {
                    @Override
                    public void onNext(WatchResponse response) {
                        logger.info("info listener {}", response);
                        List<WatchEvent> watchEvents = response.getEvents();
                        watchEvents.forEach(watchEvent -> {
                            for (Field fieldTwo : fieldsApp) {
                                setFieldForProperties(fieldTwo, watchEvent.getKeyValue().getValue().toString(), appProperties);
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        if (!throwable.getMessage().equals("Network closed for unknown reason")) {
                            logger.info("field {} onError {}", field.getName(), throwable.toString());
                        }
                    }

                    @Override
                    public void onCompleted() {
                        logger.info("onCompleted listener {}", field.getName());
                    }
                });
                watcher.requestProgress();
            }
            return appProperties;
        } catch (Exception e) {
            logger.error("initProperties {}", e.getMessage(), e);
        }
        throw new RuntimeException("настройки не поднялись");

    }

    private static void setFieldForProperties(Field field, String keyValue, AppProperties appProperties) {
        try {
            Field fieldForSet = appProperties.getClass().getDeclaredField(field.getName());
            fieldForSet.setAccessible(true);
            fieldForSet.set(appProperties, keyValue);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            logger.error("setFieldForProperties {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
