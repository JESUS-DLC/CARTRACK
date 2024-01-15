package dev.jesusdlc.cartrack.business.exception;

public class ExistsException extends RuntimeException{

    public ExistsException(String resource) {
        super("the "+resource+" already exists");
    }

}
