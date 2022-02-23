package dev.facturador.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public record RegisterResponse(String  accesToken, String  refreshToken) implements IApiResponse{
}
