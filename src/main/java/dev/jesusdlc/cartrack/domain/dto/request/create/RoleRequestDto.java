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
public class RoleRequestDto {

    @NotEmpty(message = "el rol necesita un nombre")
    private String name;
}
