package dev.jesusdlc.cartrack.domain.dto.request.update;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRequestUpdateDto {

    private Long id;
    @NotEmpty(message = "el usuario debe tener un nombre")
    private String name;
    @NotNull(message = "el usuario debe tener un email")
    private String email;
    private String password;

}
