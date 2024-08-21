package my.pet.ticket.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * TicketForCreateDto для создания сущности задачи
 *
 * @author <a href="mailto:baranov.alexalex@gmail.com">abaranov</a>
 */
@Builder
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponseDto {

    @Schema(description = "Id задачи")
    private Long id;

    @Schema(description = "Название задачи")
    private String name;

    @Schema(description = "Название задачи")
    private String description;
}
