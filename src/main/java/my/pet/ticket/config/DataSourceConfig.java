package my.pet.ticket.config;

import com.zaxxer.hikari.HikariConfig;
import my.pet.ticket.config.dto.AppProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig extends HikariConfig {

    @Bean
    public DataSource getDataSource(AppProperties appProperties) {
        appProperties.getDatabaseUrl();
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(appProperties.getDatabaseUrl());
        dataSourceBuilder.username(appProperties.getDatabaseUser());
        dataSourceBuilder.password(appProperties.getDatabasePassword());
        return dataSourceBuilder.build();
    }
}
