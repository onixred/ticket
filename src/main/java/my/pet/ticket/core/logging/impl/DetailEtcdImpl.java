package my.pet.ticket.core.logging.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import my.pet.ticket.core.logging.Detail;

public class DetailEtcdImpl implements Detail {
    private final ObjectNode data;

    public DetailEtcdImpl(String key, String value) {
        ObjectMapper objectMapper = new ObjectMapper();
        this.data = objectMapper.createObjectNode();
        this.data.put(key, value);
    }

    @Override
    public ObjectNode toDetails() {
        return this.data;
    }
}
