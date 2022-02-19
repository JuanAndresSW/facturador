package dev.facturador.dto.security;

import lombok.*;

/**
 * JWTdto para el envio del token
 */
@Getter @Setter @ToString
public class ApiResponse {
    private String response;

    public ApiResponse(String response) {
        this.response = response;
    }

    public ApiResponse() {

    }
}
