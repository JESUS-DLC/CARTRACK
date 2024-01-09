package dev.jesusdlc.cartrack.domain.dto.request.create;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRequestDto {

    @NotEmpty(message = "el usuario debe tener un nombre")
    private String name;
    @NotEmpty(message = "el usuario debe tener un email")
    private String email;
    @NotEmpty(message = "el usuario debe tener una contrasena")
    @Size(min = 8, message = "la contraseña debe tener al menos 8 caracteres")
    private String password;
    @NotEmpty(message = "el usuario debe tener la confirmacion de la contrasena")
    @Size(min = 8, message = "la contraseña debe tener al menos 8 caracteres")
    private String repeatedPassword;

}
