package my.pet.ticket.config;

import ch.qos.logback.classic.Logger;
import io.etcd.jetcd.*;
import io.etcd.jetcd.kv.GetResponse;
import io.etcd.jetcd.watch.WatchEvent;
import io.etcd.jetcd.watch.WatchResponse;
import my.pet.ticket.config.dto.AppProperties;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Configuration
public class PropertiesConfig {

    private static Logger logger = (Logger) LoggerFactory.getLogger(PropertiesConfig.class);

    @Bean
    public AppProperties initProperties(ApplicationContext applicationContext) {
        logger.info("start init properties");
        try {
            //Если закрываем клиента то listener завершают свою работу
            Client client = Client.builder().endpoints("http://127.0.0.1:2380").build();
            KV kv = client.getKVClient();

            AppProperties appProperties = new AppProperties();

            Field[] fields = appProperties.getClass().getDeclaredFields();

            for (Field field : fields) {
                ByteSequence byteSequence = ByteSequence.from(field.getName().getBytes());
                CompletableFuture<GetResponse> completableFuture = kv.get(byteSequence);
                GetResponse getResponse = completableFuture.get();
                List<KeyValue> keyValues = getResponse.getKvs();
                keyValues.forEach(keyValue -> {
                    if (keyValue.getKey().toString().equals("databaseUrl")) {
                        logger.info("{}: {}", field.getName(), keyValue.getValue().toString());
                        setFieldForProperties(field, keyValue, appProperties);
                        appProperties.setDatabaseUrl(keyValue.getValue().toString());
                    }
                    if (keyValue.getKey().toString().equals("databasePassword")) {
                        logger.info("{}: {}", field.getName(), keyValue.getValue().toString());
                        setFieldForProperties(field, keyValue, appProperties);
                        appProperties.setDatabasePassword(keyValue.getValue().toString());
                    }
                    if (keyValue.getKey().toString().equals("databaseUser")) {
                        logger.info("{}: {}", field.getName(), keyValue.getValue().toString());
                        setFieldForProperties(field, keyValue, appProperties);
                        appProperties.setDatabaseUser(keyValue.getValue().toString());
                    }
                });
            }

            for (Field field : fields) {
                ByteSequence byteSequence = ByteSequence.from(field.getName().getBytes());

                Watch watch = client.getWatchClient();
                Watch.Watcher watcher = watch.watch(byteSequence, new Watch.Listener() {
                    @Override
                    public void onNext(WatchResponse response) {
                        logger.info("info listener {}", response);
                        List<WatchEvent> watchEvents = response.getEvents();
                        watchEvents.forEach(watchEvent -> {
                            if (watchEvent.getKeyValue().getKey().toString().equals(field.getName())) {
                                appProperties.setDatabaseUrl(watchEvent.getKeyValue().getValue().toString());
                            }
                        });

//                        if (field.getName().equals("databaseUrl") || field.getName().equals("databasePassword") || field.getName().equals("databaseUser")) {
//                            DefaultSingletonBeanRegistry registry = (DefaultSingletonBeanRegistry) applicationContext.getAutowireCapableBeanFactory();
//                            registry.destroySingleton("dataSourcePostgresqlGeneral");
//                            registry.registerSingleton("dataSourcePostgresqlGeneral", getDataSource(appProperties));
//                            logger.info("destroySingleton");
//                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        if(!throwable.getMessage().equals("Network closed for unknown reason")){
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
            e.printStackTrace();
        }
        throw new RuntimeException("настройки не поднялись");

    }

    private static void setFieldForProperties(Field field, KeyValue keyValue, AppProperties appProperties) {
        try {
            Field fieldForSet = appProperties.getClass().getDeclaredField(field.getName());
            fieldForSet.setAccessible(true);
            fieldForSet.set(appProperties, keyValue.getValue().toString());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            logger.info("{}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
