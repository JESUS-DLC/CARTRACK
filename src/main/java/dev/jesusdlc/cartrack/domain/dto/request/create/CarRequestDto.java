package dev.jesusdlc.cartrack.domain.dto.request.create;

import dev.jesusdlc.cartrack.domain.entity.Brand;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarRequestDto {

    @NotNull(message = "brand is required")
    private Long brand;
    @NotEmpty(message = "model is required")
    private String model;
    @NotNull(message = "year required")
    private Integer year;
}
