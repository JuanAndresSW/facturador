package dev.facturador.auth.application;

import dev.facturador.shared.infrastructure.JWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.HashSet;


public final class JWTOfAuth implements JWT<UsernamePasswordAuthenticationToken> {

    @Override
    public UsernamePasswordAuthenticationToken createUserByToken(String authHeader) {
        var token = authHeader.substring("Bearer ".length());
        var decodedJWT = createDecoder(token);
        var username = decodedJWT.getSubject();
        var rol = getClaimRol(decodedJWT);
        return new UsernamePasswordAuthenticationToken(username, null,
                new HashSet<GrantedAuthority>(Collections.singleton(new SimpleGrantedAuthority(rol))));
    }

    @Override
    public Boolean verifyToken(String auth) {
        return Boolean.TRUE.equals(StringUtils.hasText(auth) && auth.startsWith("Bearer "));
    }
}
