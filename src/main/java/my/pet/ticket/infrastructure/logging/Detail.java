package my.pet.ticket.infrastructure.logging;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface Detail {
    
    ObjectNode toDetails();

}
