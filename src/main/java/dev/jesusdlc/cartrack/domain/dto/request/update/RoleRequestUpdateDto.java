package dev.jesusdlc.cartrack.domain.dto.request.update;

import dev.jesusdlc.cartrack.domain.dto.request.create.RoleRequestDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleRequestUpdateDto extends RoleRequestDto {

    @NotNull(message = "el rol necesita un id")
    private Long id;

}
