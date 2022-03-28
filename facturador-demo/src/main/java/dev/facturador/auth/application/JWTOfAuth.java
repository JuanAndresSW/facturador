package dev.facturador.auth.application;

import dev.facturador.shared.infrastructure.JWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.HashSet;


public final class JWTOfAuth implements JWT<UsernamePasswordAuthenticationToken> {

    /**
     * Crea un usuario con el Bearer JWT
     *
     * @param authHeader Bearer JWT
     * @return {@link UsernamePasswordAuthenticationToken} user
     */
    @Override
    public UsernamePasswordAuthenticationToken createUserByToken(String authHeader) {
        var token = authHeader.substring("Bearer ".length());
        var decodedJWT = createDecoder(token);
        var email = decodedJWT.getSubject();
        var role = getClaimRol(decodedJWT);
        return new UsernamePasswordAuthenticationToken(email, null,
                new HashSet<GrantedAuthority>(Collections.singleton(new SimpleGrantedAuthority(role))));
    }
}
