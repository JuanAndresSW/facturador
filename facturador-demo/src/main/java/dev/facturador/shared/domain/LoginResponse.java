package dev.facturador.shared.domain;


public record LoginResponse(String username,
                            Long IDTrader,
                            String role,
                            Integer active,
                            Integer pasive,
                            String accessToken,
                            String refreshToken) {

    public LoginResponse(String username, String role, String accesToken, String refreshToken) {
        this(username, null, role, null, null, accesToken, refreshToken);
    }
}
