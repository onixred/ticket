package my.pet.ticket.server.common.logging;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface Event {

    ObjectNode toDetails ();

}
