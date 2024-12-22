package dev.facturador.global.domain;

public final class ResponseNull extends RuntimeException{
    public ResponseNull(String message) {
        super(message);
    }
}
