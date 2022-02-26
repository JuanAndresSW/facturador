package dev.facturador.dto.response;

public record RegisterResponse(String accesToken, String refreshToken) implements IApiResponse {
}
