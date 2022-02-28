package dev.facturador.mainaccount.domain.dto;

import dev.facturador.gategay.responsecore.IApiResponse;

public record RegisterResponse(String accesToken, String refreshToken) implements IApiResponse {
}
