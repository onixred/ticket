package my.pet.ticket.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class DetailLog implements Detail {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final String key;
    private final String value;

    DetailLog(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public ObjectNode toDetails() {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put(key, value);
        return objectNode;
    }

}
