package dev.jesusdlc.cartrack.presentation.advice;

import dev.jesusdlc.cartrack.business.exception.EmailException;
import dev.jesusdlc.cartrack.business.exception.ExistsException;
import dev.jesusdlc.cartrack.business.exception.InvalidPasswordException;
import dev.jesusdlc.cartrack.business.exception.NotFoundException;
import dev.jesusdlc.cartrack.domain.util.ErrorMessage;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ErrorMessage> notFoundException(NotFoundException exception){
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now().format(formatter));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(ExistsException.class)
    ResponseEntity<ErrorMessage> existsException(ExistsException exception){
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now().format(formatter));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    ResponseEntity<ErrorMessage> invalidPasswordException(InvalidPasswordException exception){
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(),HttpStatus.BAD_REQUEST,LocalDateTime.now().format(formatter));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(EmailException.class)
    ResponseEntity<ErrorMessage> emailException(EmailException exception){
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(),HttpStatus.BAD_REQUEST,LocalDateTime.now().format(formatter));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }



    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error ->{
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName,message);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity<ErrorMessage> dataIntegrityViolationException(DataIntegrityViolationException exception){
        ErrorMessage errorMessage = new ErrorMessage("error en los datos enviados",HttpStatus.BAD_REQUEST,LocalDateTime.now().format(formatter));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
