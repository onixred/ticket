package my.pet.ticket.core.etcd;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EtcdProperty {
    private String endpoints;
    private long requestTimeout;
    private long dialKeepAliveTime;
    private long dialKeepAliveTimeout;
    private long dialTimeout;
    private String keysPrefix;
    private String componentName;
    private String dc;
    private String instanceName;
}
