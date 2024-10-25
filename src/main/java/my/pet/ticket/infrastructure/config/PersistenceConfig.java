package my.pet.ticket.infrastructure.config;

import my.pet.ticket.infrastructure.etcd.Etcd;
import my.pet.ticket.infrastructure.etcd.EtcdProperty;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class PersistenceConfig {

    private static final String DATASOURCE_URL_KEY = "datasource-url";
    private static final String DATASOURCE_USERNAME_KEY = "datasource-username";
    private static final String DATASOURCE_PASSWORD_KEY = "datasource-password";

    private final Environment environment;

    PersistenceConfig(Environment environment, EtcdProperty etcdProperty) {
        this.environment = environment;
        Etcd.init(etcdProperty);
    }

    @Bean
    DataSource dataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        Etcd.readEtcd(String.class, DATASOURCE_URL_KEY, environment.getProperty(DATASOURCE_URL_KEY), dataSourceBuilder::url);
        Etcd.readEtcd(String.class, DATASOURCE_USERNAME_KEY, environment.getProperty(DATASOURCE_USERNAME_KEY), dataSourceBuilder::username);
        Etcd.readEtcd(String.class, DATASOURCE_PASSWORD_KEY, environment.getProperty(DATASOURCE_PASSWORD_KEY), dataSourceBuilder::password);
        return dataSourceBuilder.build();
    }

}
