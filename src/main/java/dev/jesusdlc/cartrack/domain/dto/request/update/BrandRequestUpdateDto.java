package dev.jesusdlc.cartrack.domain.dto.request.update;

import dev.jesusdlc.cartrack.domain.dto.request.create.BrandRequestDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BrandRequestUpdateDto extends BrandRequestDto {

    @NotNull(message = "id is required")
    private Long id;

}
