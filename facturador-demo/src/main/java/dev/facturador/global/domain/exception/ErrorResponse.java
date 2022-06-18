package dev.facturador.global.domain.exception;

public record ErrorResponse(String mensaje, String detalles) {
    public ErrorResponse(String mensaje) {
        this(mensaje, null);
    }
}
