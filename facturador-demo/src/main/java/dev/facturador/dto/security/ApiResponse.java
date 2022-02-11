package dev.facturador.dto.security;

import lombok.*;

/**
 * JWTdto para el envio del token
 */
@Getter @Setter @ToString
public final class ApiResponse {
    private String token;
    private String bearer = "Bearer";

    public ApiResponse(String token) {
        this.token = token;
    }
}
