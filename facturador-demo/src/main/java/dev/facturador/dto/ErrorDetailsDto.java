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
    private String causa;

    public ErrorDetailsDto(Date marcaDeTiempo, String mensaje) {
        this();
        this.marcaDeTiempo = marcaDeTiempo;
        this.mensaje = mensaje;
    }

    public ErrorDetailsDto(Date marcaDeTiempo, String mensaje, String detalles) {
        this();
        this.marcaDeTiempo = marcaDeTiempo;
        this.mensaje = mensaje;
        this.detalles = detalles;
    }

    public ErrorDetailsDto(Date marcaDeTiempo, String mensaje, String detalles, String causa) {
        this.marcaDeTiempo = marcaDeTiempo;
        this.mensaje = mensaje;
        this.detalles = detalles;
        this.causa = causa;
    }

    public ErrorDetailsDto() {
        super();
    }
}
