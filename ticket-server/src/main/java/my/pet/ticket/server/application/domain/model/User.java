package my.pet.ticket.server.application.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User user)) {
            return false;
        }
        return Objects.equals(userId, user.userId) && Objects.equals(role, user.role)
            && Objects.equals(fullName, user.fullName) && Objects.equals(login,
            user.login) && Objects.equals(active, user.active) && Objects.equals(
            suspended, user.suspended);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, role, fullName, login, active, suspended);
    }
}
