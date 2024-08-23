package my.pet.ticket.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public record DetailLog(String key, String value) implements Detail {

    private  final static ObjectMapper  objectMapper = new ObjectMapper();

    @Override
    public ObjectNode toDetails() {
        ObjectNode node =  objectMapper.createObjectNode();
        node.put(key, value);

        return node;
    }
}
