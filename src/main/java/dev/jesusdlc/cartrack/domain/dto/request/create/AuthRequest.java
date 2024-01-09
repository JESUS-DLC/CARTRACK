package dev.jesusdlc.cartrack.domain.dto.request.create;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthRequest implements Serializable {
    @NotEmpty(message = "el usuario debe tener un email")
    private String email;
    @NotEmpty(message = "el usuario debe tener una contrasena")
    private String password;
}
