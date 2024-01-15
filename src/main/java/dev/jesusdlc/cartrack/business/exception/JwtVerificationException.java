package dev.jesusdlc.cartrack.business.exception;

public class JwtVerificationException extends RuntimeException{
    public JwtVerificationException() {
        super("token exception");
    }

    public JwtVerificationException(String message) {
        super(message);
    }
}
