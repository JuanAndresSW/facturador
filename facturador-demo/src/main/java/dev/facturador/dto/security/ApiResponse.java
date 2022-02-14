package dev.facturador.dto.security;

import lombok.*;

/**
 * JWTdto para el envio del token
 */
@Getter @Setter @ToString
public class ApiResponse {
    private String token;
    private String bearer = "Bearer";

    public ApiResponse(String token) {
        this.token = token;
    }

    public ApiResponse() {

    }
}
