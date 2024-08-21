package my.pet.ticket.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

public class OpenApiConfiguration {

    @Bean
    public OpenAPI usersOpenAPI() {
        return new OpenAPI().info(new Info().title("My crm API")
                .description("My crm API")
                .version("1.0"));
    }
}
