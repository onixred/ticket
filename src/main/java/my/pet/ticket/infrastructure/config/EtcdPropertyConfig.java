package my.pet.ticket.infrastructure.config;

import my.pet.ticket.infrastructure.etcd.EtcdProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EtcdPropertyConfig {

    @Value("${etcd.endpoints}")
    private String[] endpoints;

    @Value("${etcd.request-timeout}")
    private String requestTimeout;

    @Value("${etcd.dial-keep-alive-time}")
    private String dialKeepAliveTime;

    @Value("${etcd.dial-keep-alive-timeout}")
    private String dialKeepAliveTimeout;

    @Value("${etcd.dial-timeout}")
    private String dialTimeout;

    @Value("${etcd.keys-prefix}")
    private String keysPrefix;

    @Value("${etcd.component-name}")
    private String componentName;

    @Value("${etcd.dc}")
    private String dc;

    @Value("${etcd.instance-name}")
    private String instanceName;

    @Bean
    EtcdProperty etcdProperty() {
        return EtcdProperty.builder()
                .endpoints(endpoints)
                .requestTimeout(requestTimeout)
                .dialKeepAliveTime(dialKeepAliveTime)
                .dialKeepAliveTimeout(dialKeepAliveTimeout)
                .dialTimeout(dialTimeout)
                .keysPrefix(keysPrefix)
                .componentName(componentName)
                .dc(dc)
                .instanceName(instanceName)
                .build();
    }

}
