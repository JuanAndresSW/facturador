package dev.facturador.dto;

import lombok.*;

import java.util.Date;

/**
 * Dto para Excepciones
 */
@Getter @Setter @ToString @AllArgsConstructor
public class ErrorDetailsDto {
    private Date marcaDeTiempo;
    private String mensaje;
    private String detalles;

}
