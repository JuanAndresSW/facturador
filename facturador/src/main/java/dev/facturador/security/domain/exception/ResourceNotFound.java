package dev.facturador.security.domain.exception;

public final class ResourceNotFound extends RuntimeException {
    public ResourceNotFound(String message) {
        super(message);
    }
}
