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

    @NotEmpty(message = "name is required")
    private String name;
    @NotEmpty(message = "email is required")
    private String email;
    @NotEmpty(message = "username is required")
    @Size(min = 8, message = "username must be at least 8 characters")
    private String username;
    @NotEmpty(message = "password is required")
    @Size(min = 8, message = "Password must be at least 8 characters ")
    private String password;
    @NotEmpty(message = "password confirmation is required")
    @Size(min = 8, message = "Password must be at least 8 characters ")
    private String repeatedPassword;

}
