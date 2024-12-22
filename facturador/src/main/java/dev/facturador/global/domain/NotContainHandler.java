package dev.facturador.global.domain;

public final class NotContainHandler extends RuntimeException {

    public NotContainHandler(String message) {
        super(message);
    }
}
