package dev.jesusdlc.cartrack.domain.dto.request.create;

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
public class ServiceRequestDto {

    @NotEmpty(message = "name is required")
    private String name;
    @NotEmpty(message = "description is required")
    private String description;
    @NotNull(message = "cost is required")
    private BigDecimal cost;
    @NotNull(message = "date is required")
    private LocalDate date;
    @NotNull(message = "mileage is required")
    private Long mileage;
    @NotEmpty(message = "place is required")
    private String place;
    @NotNull(message = "status is required")
    private Boolean status;


}
