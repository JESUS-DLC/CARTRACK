package dev.jesusdlc.cartrack.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jesusdlc.cartrack.domain.util.ErrorMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String message = "You do not have the necessary permissions to access this feature";
        ErrorMessage errorMessage = new ErrorMessage(message, HttpStatus.FORBIDDEN, LocalDateTime.now().format(formatter));
        String errorMessageAsJson = new ObjectMapper().writeValueAsString(errorMessage);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.getWriter().write(errorMessageAsJson);
    }
}
