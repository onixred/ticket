package my.pet.ticket.model.dto;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponseDto {

    private Long id;

    private String name;

    private String description;
}
