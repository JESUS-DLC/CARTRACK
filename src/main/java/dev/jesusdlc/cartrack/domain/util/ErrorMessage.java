package dev.jesusdlc.cartrack.domain.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Service
public class ErrorMessage {
    private String message;
    private HttpStatus status;
    private String date;
}
