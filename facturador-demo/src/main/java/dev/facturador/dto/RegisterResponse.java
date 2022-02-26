package dev.facturador.dto;

public record RegisterResponse(String accesToken, String refreshToken) implements IApiResponse {
}
