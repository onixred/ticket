package my.pet.ticket.infrastructure.logging;

import com.fasterxml.jackson.databind.node.ObjectNode;
import my.pet.ticket.infrastructure.utils.JsonUtils;

public class DetailLog implements Detail {

    private final String key;
    private final String value;

    DetailLog(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public ObjectNode toDetails() {
        return JsonUtils.createNode(key, value);
    }

}
