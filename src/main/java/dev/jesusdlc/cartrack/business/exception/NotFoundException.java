package dev.jesusdlc.cartrack.business.exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException(long id) {
        super("No se encontro el recurso con id "+id+" solicitado");
    }
}
