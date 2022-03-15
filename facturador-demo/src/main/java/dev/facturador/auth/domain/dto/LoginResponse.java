package dev.facturador.auth.domain.dto;


public record LoginResponse(String username, String rol, Integer activos, Integer pasivos, String accessToken,
                            String refreshToken) {

    public LoginResponse(String username, String rol, String accesToken, String refreshToken) {
        this(username, rol, null, null, accesToken, refreshToken);
    }
}
