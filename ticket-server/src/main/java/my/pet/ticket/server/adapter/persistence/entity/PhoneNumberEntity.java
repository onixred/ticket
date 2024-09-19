package my.pet.ticket.server.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(schema = "pet_project", name = "phone_numbers")
@SequenceGenerator(
        name = "phone_number_id_pk_seq",
        schema = "pet_project",
        sequenceName = "phone_number_id_pk_seq",
        initialValue = 1001,
        allocationSize = 0
)
public class PhoneNumberEntity extends AbstractEntity {

    @EmbeddedId
    private PhoneNumberIdEntity id;

    @Column(name = "national_prefix")
    private Integer nationalPrefix;

    @Column(name = "region_code")
    private Integer regionCode;

    @Column(name = "number")
    private Integer number;

    @Column(name = "full_number")
    private Integer fullNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneNumberEntity that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(nationalPrefix, that.nationalPrefix) && Objects.equals(regionCode, that.regionCode) && Objects.equals(number, that.number) && Objects.equals(fullNumber, that.fullNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nationalPrefix, regionCode, number, fullNumber);
    }
}
