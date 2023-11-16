package dev.jesusdlc.cartrack.business.exception;

public class ExistsException extends RuntimeException{
    public ExistsException() {
        super("el recurso ya existe");
    }
}
