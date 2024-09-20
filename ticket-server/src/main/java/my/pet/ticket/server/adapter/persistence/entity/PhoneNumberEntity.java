package my.pet.ticket.server.adapter.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "pet_project", name = "phone_numbers")
@SequenceGenerator(
        name = "phone_numbers_id_pk_seq",
        schema = "pet_project",
        sequenceName = "phone_numbers_id_pk_seq",
        initialValue = 1001,
        allocationSize = 0
)
public class PhoneNumberEntity
        extends AbstractEntity {

    @EmbeddedId
    private PhoneNumberIdEntity id;

    @Column(name = "national_prefix")
    private Integer nationalPrefix;

    @Column(name = "region_code")
    private Integer regionCode;

    @Column(name = "number")
    private Integer number;

    @Column(name = "full_number")
    private String fullNumber;

    @Builder
    public PhoneNumberEntity(
            Long phoneNumberId,
            Long clientId,
            Integer nationalPrefix,
            Integer regionCode,
            Integer number,
            String fullNumber,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            Boolean deleted
    ) {
        this.id = new PhoneNumberIdEntity();
        this.id.setPhoneNumberId(phoneNumberId);
        this.id.setClientId(clientId);
        this.nationalPrefix = nationalPrefix;
        this.regionCode = regionCode;
        this.number = number;
        this.fullNumber = fullNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PhoneNumberEntity that)) {
            return false;
        }
        return Objects.equals(id, that.id) &&
                Objects.equals(nationalPrefix, that.nationalPrefix) &&
                Objects.equals(regionCode, that.regionCode) &&
                Objects.equals(number, that.number) &&
                Objects.equals(fullNumber, that.fullNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nationalPrefix, regionCode, number, fullNumber);
    }

}
