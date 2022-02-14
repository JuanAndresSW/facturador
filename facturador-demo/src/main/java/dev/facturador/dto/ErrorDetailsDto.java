package dev.facturador.dto;

import dev.facturador.dto.security.ApiResponse;
import lombok.*;

import java.util.Date;

/**
 * Dto para Excepciones
 */
@Getter @Setter @ToString
public class ErrorDetailsDto extends ApiResponse {
    private Date marcaDeTiempo;
    private String mensaje;
    private String detalles;

    public ErrorDetailsDto(Date marcaDeTiempo, String mensaje, String detalles) {
        this();
        this.marcaDeTiempo = marcaDeTiempo;
        this.mensaje = mensaje;
        this.detalles = detalles;
    }

    public ErrorDetailsDto() {
        super();
    }
}
