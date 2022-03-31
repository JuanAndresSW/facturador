package dev.facturador.shared.domain;


public record LoginResponse(String username, String role, Integer active, Integer pasive, String accessToken,
                            String refreshToken) {

    public LoginResponse(String username, String role, String accesToken, String refreshToken) {
        this(username, role, null, null, accesToken, refreshToken);
    }
}
