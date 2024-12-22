package dev.facturador.security.domain;

public record Error(String code, String message, String description) {
    public Error(String mensaje) {
        this(null, mensaje, null);
    }

    public Error(String mensaje, String description) {
        this(null, mensaje, description);
    }
}
