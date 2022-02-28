package dev.facturador.gategay.exception;

import java.util.Date;

public record ErrorResponse(Date marcaDeTiempo, String mensaje, String detalles) {
    public ErrorResponse(Date marcaDeTiempo, String mensaje) {
        this(marcaDeTiempo, mensaje, null);
    }
}
