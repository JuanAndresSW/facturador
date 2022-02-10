package dev.facturador.dto.security;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * JWTdto para el envio del token
 */
@Getter @Setter @ToString
public final class JwtDto {
    private String token;
    private String bearer = "Bearer";
    private String userName;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtDto(String token, String userName, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.userName = userName;
        this.authorities = authorities;
    }
}
