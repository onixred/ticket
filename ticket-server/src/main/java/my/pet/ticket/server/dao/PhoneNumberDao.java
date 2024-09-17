package my.pet.ticket.server.dao;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(schema = "pet_project", name = "phone_numbers")
@EqualsAndHashCode(callSuper = true)
public class PhoneNumberDao extends AbstractDao {

    @EmbeddedId
    private PhoneNumberIdDao id;

    @Column(name = "national_prefix")
    private Integer nationalPrefix;

    @Column(name = "region_code")
    private Integer regionCode;

    @Column(name = "number")
    private Integer number;

    @Column(name = "full_number")
    private Integer fullNumber;

}
