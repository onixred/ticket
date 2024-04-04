package my.pet.ticket.config;

import com.zaxxer.hikari.HikariConfig;
import my.pet.ticket.config.dto.AppProperties;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig{

    @Bean("dataSourcePostgresqlGeneral")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public DataSource getDataSource(AppProperties appProperties) {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url(appProperties.getDatabaseUrl());
        dataSourceBuilder.username(appProperties.getDatabaseUser());
        dataSourceBuilder.password(appProperties.getDatabasePassword());
        return dataSourceBuilder.build();
    }
}
