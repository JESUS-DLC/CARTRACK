package dev.jesusdlc.cartrack.domain.dto.request.create;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BrandRequestDto {
    @NotEmpty(message = "la marca debe tener un nombre")
    private String name;
}
