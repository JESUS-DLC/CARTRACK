package dev.jesusdlc.cartrack.domain.dto.request.update;

import dev.jesusdlc.cartrack.domain.dto.request.create.BrandRequestDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BrandRequestUpdateDto extends BrandRequestDto {

    @NotNull(message = "la marca debe tener un id")
    private Long id;
}
