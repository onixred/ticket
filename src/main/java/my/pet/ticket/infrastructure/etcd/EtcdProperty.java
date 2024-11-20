package my.pet.ticket.infrastructure.etcd;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EtcdProperty {

    private String[] endpoints;

}
