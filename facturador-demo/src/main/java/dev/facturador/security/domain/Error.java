package dev.facturador.security.domain;

public record Error(String code, String message, String path) {
    public Error(String mensaje) {
        this(null, mensaje, null);
    }

    public Error(String mensaje, String path) {
        this(null, mensaje, path);
    }
}
