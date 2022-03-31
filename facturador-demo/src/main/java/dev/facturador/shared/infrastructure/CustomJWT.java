package dev.facturador.shared.infrastructure;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.xml.bind.DatatypeConverter;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

public class CustomJWT {
    private String secrectKey;
    private long expDateDefined;

    public CustomJWT(){
        this.secrectKey = "$argon2id$v=19$m=2048,t=2,p=1$F7XsIVx3YSVL6tGdyeGyrA$dLXD9Clq4po8/dL6b0IudGmgGyr+4cHNTM4fjqG5LDw";
        this.expDateDefined = 14400000;
    }

    public UsernamePasswordAuthenticationToken createUserByToken(String authHeader) {
        var token = authHeader.substring("Bearer ".length());
        var decodedJWT = createDecoder(token);
        var email = decodedJWT.getSubject();
        var role = getClaimRol(decodedJWT);
        return new UsernamePasswordAuthenticationToken(email, null,
                new HashSet<GrantedAuthority>(Collections.singleton(new SimpleGrantedAuthority(role))));
    }

    public Boolean verifyToken(String auth) {
        return Boolean.TRUE.equals(StringUtils.hasText(auth) && auth.startsWith("Bearer "));
    }

    public String createAccesToken(String email, Collection<? extends GrantedAuthority> role, String url) {
        return com.auth0.jwt.JWT.create()
                .withSubject(email)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + expDateDefined))
                .withIssuer(url)
                .withClaim("role", role.stream().toList().get(0).getAuthority())
                .sign(signKey());
    }

    public String createRefreshToken(String email, String url) {
        return com.auth0.jwt.JWT.create()
                .withSubject(email)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + expDateDefined + 259200000))
                .withIssuer(url)
                .sign(signKey());
    }

    public Algorithm signKey() {
        return Algorithm.HMAC256(DatatypeConverter.parseBase64Binary(secrectKey));
    }

    public DecodedJWT createDecoder(String token) {
        return com.auth0.jwt.JWT.require(this.signKey()).build().verify(token);
    }

    public String getClaimRol(DecodedJWT decodedJWT) {
        return decodedJWT.getClaim("role").asString();
    }
}
