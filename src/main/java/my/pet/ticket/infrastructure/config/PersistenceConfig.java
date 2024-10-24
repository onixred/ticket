package my.pet.ticket.infrastructure.config;

import jakarta.persistence.EntityManagerFactory;
import my.pet.ticket.infrastructure.etcd.Etcd;
import my.pet.ticket.infrastructure.etcd.EtcdProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
public class PersistenceConfig {

    private final Environment environment;
    private static final String DATASOURCE_URL_KEY = "datasource-url";
    private static final String DATASOURCE_USERNAME_KEY = "datasource-username";
    private static final String DATASOURCE_PASSWORD_KEY = "datasource-password";

    PersistenceConfig(Environment environment, EtcdProperty etcdProperty) {
        this.environment = environment;
        Etcd.init(etcdProperty);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        Etcd.readEtcd(String.class, DATASOURCE_URL_KEY, environment.getProperty(DATASOURCE_URL_KEY), dataSource::setUrl);
        Etcd.readEtcd(String.class, DATASOURCE_USERNAME_KEY, environment.getProperty(DATASOURCE_USERNAME_KEY), dataSource::setUsername);
        Etcd.readEtcd(String.class, DATASOURCE_PASSWORD_KEY, environment.getProperty(DATASOURCE_PASSWORD_KEY), dataSource::setPassword);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPackagesToScan("my.pet.ticket.adapter.out.persistence.entity");
        return entityManagerFactory;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}
