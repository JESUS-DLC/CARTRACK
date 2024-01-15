package dev.jesusdlc.cartrack.business.service.impl;

import dev.jesusdlc.cartrack.business.exception.EmailException;
import dev.jesusdlc.cartrack.business.exception.InvalidPasswordException;
import dev.jesusdlc.cartrack.business.exception.NotFoundException;
import dev.jesusdlc.cartrack.business.exception.UsernameException;
import dev.jesusdlc.cartrack.business.mapper.UserMapper;
import dev.jesusdlc.cartrack.business.service.AuthService;
import dev.jesusdlc.cartrack.business.service.RoleService;
import dev.jesusdlc.cartrack.config.security.JwtService;
import dev.jesusdlc.cartrack.domain.dto.request.create.AuthRequest;
import dev.jesusdlc.cartrack.domain.dto.request.create.UserRequestDto;
import dev.jesusdlc.cartrack.domain.dto.response.AuthResponse;
import dev.jesusdlc.cartrack.domain.dto.response.UserResponseDto;
import dev.jesusdlc.cartrack.domain.entity.Role;
import dev.jesusdlc.cartrack.domain.entity.Usuario;
import dev.jesusdlc.cartrack.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleService roleService;
    private final String RESOURCE = "car";

    @Override
    public UserResponseDto register(UserRequestDto userRequestDto) {
        validateEmail(userRequestDto);
        validateUsername(userRequestDto);
        validatePassword(userRequestDto);
        Usuario user = userMapper.toUser(userRequestDto);
        user.setEnabled(true);
        Role role = roleService.findDefaultRole().orElseThrow(()-> new NotFoundException("role"));
        user.setRole(role);

        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        Usuario userSaved = userRepository.save(user);
        UserResponseDto userResponseDto = userMapper.toUserResponseDto(userSaved);
        return userResponseDto;
    }

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(),
                authRequest.getPassword()
        );
        authenticationManager.authenticate(authentication);
        Usuario usuario = userRepository.findByUsername(authRequest.getUsername()).get();

        String jwt = jwtService.generateToken(usuario);
        return new AuthResponse(jwt);
    }

    public String getAuthUsername(){
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return (String)authenticationToken.getPrincipal();
    }

    private void validatePassword(UserRequestDto userRequestDto){
        if(!userRequestDto.getPassword().equals(userRequestDto.getRepeatedPassword()))
            throw new InvalidPasswordException();
    }

    private void validateEmail(UserRequestDto userRequestDto){
        if(userRepository.existsByEmailIgnoreCase(userRequestDto.getEmail()))
            throw new EmailException();
    }

    private void validateUsername(UserRequestDto userRequestDto){
        if(userRepository.existsByUsernameIgnoreCase(userRequestDto.getUsername()))
            throw new UsernameException();
    }

}
