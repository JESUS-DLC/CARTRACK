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

    @NotNull(message = "el carro debe tener una marca")
    private Long brand;
    @NotEmpty(message = "el carro debe tener un modelo")
    private String model;
    @NotNull(message = "el carro debe tener un año")
    private Integer year;
}
