package dev.facturador.global.infrastructure.adapters;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.util.StringUtils;

import java.util.Date;

public class CustomJWT {
    private final String secrectKey;
    private final long expDateDefined;

    public CustomJWT() {
        this.secrectKey = "$argon2id$v=19$m=2048,t=2,p=1$F7XsIVx3YSVL6tGdyeGyrA$dLXD9Clq4po8/dL6b0IudGmgGyr+4cHNTM4fjqG5LDw";
        this.expDateDefined = 14400000;
    }

    public String createUserByToken(String authHeader) {
        var token = authHeader.substring("Bearer ".length());
        var decodedJWT = createDecoder(token);
        return decodedJWT.getSubject();
    }

    public Boolean verifyToken(String auth) {
        return Boolean.TRUE.equals(StringUtils.hasText(auth) && auth.startsWith("Bearer "));
    }

    public String createAccesToken(String email, String url) {
        return com.auth0.jwt.JWT.create()
                .withSubject(email)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + expDateDefined))
                .withIssuer(url)
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
        return Algorithm.HMAC256(secrectKey.getBytes());
    }

    public DecodedJWT createDecoder(String token) {
        return com.auth0.jwt.JWT.require(this.signKey()).build().verify(token);
    }

}
