package dev.jesusdlc.cartrack.business.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException() {
        super("No se encontro el recurso solicitad");
    }
}
