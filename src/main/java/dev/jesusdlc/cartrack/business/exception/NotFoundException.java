package dev.jesusdlc.cartrack.business.exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String resource) {
        super(resource+" not found");
    }

    public NotFoundException() {
        super("resource not found");
    }
}
