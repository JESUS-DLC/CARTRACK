package dev.jesusdlc.cartrack.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponseDto {

    private Long id;
    private String name;
    private String email;
    private String username;
    private String role;
}
