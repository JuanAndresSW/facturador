package dev.facturador.auth.domain.dto;


public record LoginResponse(String username, Integer activos, Integer pasivos, String accessToken,
                            String refreshToken) {

    public LoginResponse(String username, String accesToken, String refreshToken) {
        this(username, null, null, accesToken, refreshToken);
    }
}
