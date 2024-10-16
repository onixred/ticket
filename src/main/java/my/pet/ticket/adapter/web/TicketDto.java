package my.pet.ticket.adapter.web;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TicketDto {

    @NotNull
    @Size(min = 1, max = 256)
    private String title;

    @Size(min = 1, max = 4096)
    private String description;

}
