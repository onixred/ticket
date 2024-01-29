package my.pet.ticket.core.logging;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface Detail {
    ObjectNode toDetails();
}
