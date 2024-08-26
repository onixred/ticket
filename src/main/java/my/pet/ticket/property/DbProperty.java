package my.pet.ticket.property;

import lombok.Data;

/**
 * DbProperty class for
 *
 * @author <a href="mailto:baranov.alexalex@gmail.com">abaranov</a>
 */
@Data
public class DbProperty {
    private String host;
    private String username;
    private String password;
    private String driverClassName;
}
