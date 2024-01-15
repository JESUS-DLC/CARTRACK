package dev.jesusdlc.cartrack.business.exception;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException() {
        super("Passwords do not match");
    }
}
