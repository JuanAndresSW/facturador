package dev.facturador.global.domain.exception;

public record ErrorResponse(Integer code, String message, String description) {
    public ErrorResponse(String mensaje) {
        this(null, mensaje, null);
    }

    public ErrorResponse(String mensaje, String description) {
        this(null, mensaje, description);
    }
}
