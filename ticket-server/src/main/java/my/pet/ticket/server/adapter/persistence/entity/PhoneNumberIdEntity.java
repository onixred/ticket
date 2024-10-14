package my.pet.ticket.server.adapter.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.Objects;
import lombok.Data;

@Data
@Embeddable
public class PhoneNumberIdEntity {

  @Column(name = "phone_number_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pet_project.phone_numbers_id_pk_seq")
  private Long phoneNumberId;

  @Column(name = "client_id")
  private Long clientId;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PhoneNumberIdEntity that)) {
      return false;
    }
    return Objects.equals(phoneNumberId, that.phoneNumberId) && Objects.equals(clientId,
        that.clientId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(phoneNumberId, clientId);
  }

}
