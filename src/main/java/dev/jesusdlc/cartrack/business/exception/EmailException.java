package dev.jesusdlc.cartrack.business.exception;

public class EmailException extends RuntimeException{
    public EmailException() {
        super("An account already exists with that username");
    }
}
