package dev.facturador.mainaccount.domain;


public record MainAccountSingInResponse(String username,
                                        Long IDTrader,
                                        String role,
                                        Integer active,
                                        Integer pasive,
                                        String accessToken,
                                        String refreshToken) {

    public MainAccountSingInResponse(String username, String role, String accesToken, String refreshToken) {
        this(username, null, role, null, null, accesToken, refreshToken);
    }
}
