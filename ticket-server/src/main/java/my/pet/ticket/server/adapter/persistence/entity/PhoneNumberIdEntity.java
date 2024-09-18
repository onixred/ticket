package my.pet.ticket.server.adapter.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.Objects;

@Data
@Embeddable
public class PhoneNumberIdEntity {

    @Column(name = "phone_id")
    private Long phoneId;

    @Column(name = "client_id")
    private Long clientId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneNumberIdEntity that)) return false;
        return Objects.equals(phoneId, that.phoneId) && Objects.equals(clientId, that.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneId, clientId);
    }
}
