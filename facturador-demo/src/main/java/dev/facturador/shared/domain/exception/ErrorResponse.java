package dev.facturador.shared.domain.exception;

import java.util.Date;

public record ErrorResponse(String mensaje, String detalles) {
    public ErrorResponse(String mensaje) {
        this(mensaje, null);
    }
}
