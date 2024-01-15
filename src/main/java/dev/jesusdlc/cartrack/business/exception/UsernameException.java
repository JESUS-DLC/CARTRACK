package dev.jesusdlc.cartrack.business.exception;

public class UsernameException extends RuntimeException{

    public UsernameException() {
        super("An account already exists with that username");
    }
}
