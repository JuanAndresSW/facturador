package dev.facturador.dto.response;

public record LoginResponse(String username, Integer activos, Integer pasivos, String accesToken, String refreshToken) implements IApiResponse {
    public LoginResponse(String username, String accesToken, String refreshToken){
        this(username, null, null, accesToken, refreshToken);
    }
}
