package dev.jesusdlc.cartrack.business.exception;

public class ExistsException extends RuntimeException{

    public ExistsException(String resoruce) {
        super("el recurso "+resoruce+" ya existe");
    }

}
