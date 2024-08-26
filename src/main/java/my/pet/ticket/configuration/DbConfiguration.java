package my.pet.ticket.configuration;

import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import my.pet.ticket.etcd.EtcdClient;
import my.pet.ticket.property.DbProperty;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * DbConfiguration class for
 *
 * @author <a href="mailto:baranov.alexalex@gmail.com">abaranov</a>
 */
@Configuration
@RequiredArgsConstructor
public class DbConfiguration {
    public static final String DATASOURCE_HOST = "/datasource.url";
    public static final String DATASOURCE_USERNAME = "/datasource.username";
    public static final String DATASOURCE_PASSWORD = "/datasource.password";
    private final EtcdClient etcdClient;

    @Bean
    public DbProperty dbProperty(Environment environment){
        DbProperty dbProperty = new DbProperty();
        etcdClient.readEtcd(String.class, DATASOURCE_HOST, null, dbProperty::setHost);
        etcdClient.readEtcd(String.class, DATASOURCE_USERNAME, null, dbProperty::setUsername);
        etcdClient.readEtcd(String.class, DATASOURCE_PASSWORD, null, dbProperty::setPassword);
        dbProperty.setDriverClassName(environment.getProperty("datasource.driver-class-name"));
        return dbProperty;
    }

    @Bean
    public DataSource dataSource(DbProperty dbProperty){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(dbProperty.getDriverClassName());
        dataSourceBuilder.url(dbProperty.getHost());
        dataSourceBuilder.username(dbProperty.getUsername());
        dataSourceBuilder.password(dbProperty.getPassword());
        return dataSourceBuilder.build();
    }
}
