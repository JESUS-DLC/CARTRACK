package dev.jesusdlc.cartrack.business.service;

import dev.jesusdlc.cartrack.domain.dto.request.create.AuthRequest;
import dev.jesusdlc.cartrack.domain.dto.request.create.UserRequestDto;
import dev.jesusdlc.cartrack.domain.dto.response.AuthResponse;
import dev.jesusdlc.cartrack.domain.dto.response.UserResponseDto;

public interface AuthService {

    UserResponseDto register(UserRequestDto userRequestDto);
    AuthResponse login(AuthRequest authRequest);
    String getAuthUsername();
}
