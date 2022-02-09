package dev.facturador.dto;

import lombok.*;

import java.util.Date;

@Getter @Setter @ToString @AllArgsConstructor
public class ErrorDetailsDto {
    private Date marcaDeTiempo;
    private String mensaje;
    private String detalles;

}
