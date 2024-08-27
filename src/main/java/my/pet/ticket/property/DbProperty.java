package my.pet.ticket.property;

import lombok.Data;

/**
 * DbProperty class for
 *
 * @author <a href="mailto:baranov.alexalex@gmail.com">abaranov</a>
 */
@Data
public class DbProperty {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
}
