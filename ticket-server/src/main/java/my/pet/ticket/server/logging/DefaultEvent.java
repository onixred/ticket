package my.pet.ticket.server.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;

@Data
public class DefaultEvent implements Event {

    private final String name;

    public DefaultEvent(String name) {
        this.name = name;
    }

    @Override
    public ObjectNode toDetails() {
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("name", this.name);
        return objectNode;
    }

}
