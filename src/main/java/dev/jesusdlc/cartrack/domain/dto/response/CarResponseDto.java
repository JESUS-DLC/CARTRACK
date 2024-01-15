package dev.jesusdlc.cartrack.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarResponseDto {
    private long id;
    private long brandId;
    private String brand;
    private String model;
    private int year;
}
