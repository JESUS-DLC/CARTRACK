package dev.jesusdlc.cartrack.presentation.controller;

import dev.jesusdlc.cartrack.business.service.AuthService;
import dev.jesusdlc.cartrack.domain.dto.request.create.AuthRequest;
import dev.jesusdlc.cartrack.domain.dto.request.create.UserRequestDto;
import dev.jesusdlc.cartrack.domain.dto.response.AuthResponse;
import dev.jesusdlc.cartrack.domain.dto.response.UserResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(authService.register(userRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest){
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(authRequest));
    }



}
