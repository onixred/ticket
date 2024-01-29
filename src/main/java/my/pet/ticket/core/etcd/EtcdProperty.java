package my.pet.ticket.core.etcd;

public record EtcdProperty(String endpoints,
        String requestTimeout,
        String dialKeepAliveTime,
        String dialKeepAliveTimeout,
        String dialTimeout,
        String keysPrefix,
        String componentName,
        String dc,
        String instanceName) {

}
