package dev.jesusdlc.cartrack.domain.dto.request.update;


import dev.jesusdlc.cartrack.domain.dto.request.create.CarRequestDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CarRequestUpdateDto extends CarRequestDto {

    @NotNull(message = "el carro debe tener un id")
    private Long id;


}
