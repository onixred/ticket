package my.pet.ticket.server.common.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


public enum DefaultEvent implements Event {

    BP_START("Business process start"),

    BP_IN_PROGRESS("Business process in progress"),

    BP_END("Business process end"),

    DBP_START("Database process start"),

    DBP_END("Database process end");

    private final String name;

    DefaultEvent(String name) {
        this.name = name;
    }

    @Override
    public ObjectNode toDetails() {
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("name", this.name);
        return objectNode;
    }

}
