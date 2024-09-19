package my.pet.ticket.server.application.port.configuration;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestValue {

    @JsonAlias("value")
    private String value;

}
