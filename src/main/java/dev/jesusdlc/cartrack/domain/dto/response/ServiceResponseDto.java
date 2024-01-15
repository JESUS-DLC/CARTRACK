package dev.jesusdlc.cartrack.domain.dto.response;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServiceResponseDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal cost;
    private LocalDate date;
    private Long mileage;
    private String place;
    private Boolean status;
    private Long car;
}
