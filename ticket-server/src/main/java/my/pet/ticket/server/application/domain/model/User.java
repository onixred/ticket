package my.pet.ticket.server.application.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("role")
    private Role role;

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("login")
    private String login;

    @JsonProperty("active")
    private Boolean active;

    @JsonProperty("suspended")
    private Boolean suspended;

}
