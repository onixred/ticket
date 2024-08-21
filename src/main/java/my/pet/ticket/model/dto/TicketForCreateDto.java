package my.pet.ticket.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * TicketForCreateDto для создания сущности задачи
 *
 * @author <a href="mailto:baranov.alexalex@gmail.com">abaranov</a>
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TicketForCreateDto {

    @Schema(description = "Название задачи")
    @NotNull
    @Size(min = 4, max =20)
    private String name;

    @Schema(description = "Описание задачи")
    @NotNull
    @Size(min = 4, max =200)
    private String description;
}
