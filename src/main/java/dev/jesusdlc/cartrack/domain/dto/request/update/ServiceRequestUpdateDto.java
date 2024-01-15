package dev.jesusdlc.cartrack.domain.dto.request.update;

import dev.jesusdlc.cartrack.domain.dto.request.create.ServiceRequestDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServiceRequestUpdateDto extends ServiceRequestDto {
    @NotNull(message = "id is required")
    private Long id;
}
