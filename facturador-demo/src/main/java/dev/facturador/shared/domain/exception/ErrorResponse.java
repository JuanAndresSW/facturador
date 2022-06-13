package dev.facturador.shared.domain.exception;

public record ErrorResponse(String mensaje, String detalles) {
    public ErrorResponse(String mensaje) {
        this(mensaje, null);
    }
}
