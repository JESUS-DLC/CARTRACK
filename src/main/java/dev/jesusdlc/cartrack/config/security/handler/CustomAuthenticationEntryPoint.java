package dev.jesusdlc.cartrack.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jesusdlc.cartrack.domain.util.ErrorMessage;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String message;

        if(authException instanceof BadCredentialsException){
            message = "Incorrect credentials. Check your email and password and try again";
        }else {
            message = "access denied, please log in to access this feature";
        }
        ErrorMessage errorMessage = new ErrorMessage(message, HttpStatus.UNAUTHORIZED, LocalDateTime.now().format(formatter));
        String errorMessageAsJson = new ObjectMapper().writeValueAsString(errorMessage);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(errorMessageAsJson);
    }
}
