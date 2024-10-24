package my.pet.ticket.infrastructure.etcd;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EtcdProperty {

    private String[] endpoints;

    private String requestTimeout;

    private String dialKeepAliveTime;

    private String dialKeepAliveTimeout;

    private String dialTimeout;

    private String keysPrefix;

    private String componentName;

    private String dc;

    private String instanceName;

}
