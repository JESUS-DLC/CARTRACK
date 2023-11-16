package dev.jesusdlc.cartrack.domain.dto.request.create;

import dev.jesusdlc.cartrack.domain.entity.Brand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarRequestDto {
    private Long brand;
    private String model;
    private String year;
}
