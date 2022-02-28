package dev.facturador.infrastructurecore.exception;

import dev.facturador.gategay.responsecore.IApiResponse;

import java.util.Date;

public record ErrorResponse(Date marcaDeTiempo, String mensaje, String detalles) implements IApiResponse {
    public ErrorResponse(Date marcaDeTiempo, String mensaje) {
        this(marcaDeTiempo, mensaje, null);
    }
}
