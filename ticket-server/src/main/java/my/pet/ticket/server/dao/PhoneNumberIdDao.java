package my.pet.ticket.server.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class PhoneNumberIdDao {

    @Column(name = "phone_id")
    private Long phoneId;

    @Column(name = "client_id")
    private Long clientId;

}
