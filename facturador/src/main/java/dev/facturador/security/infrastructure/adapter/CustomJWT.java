package dev.facturador.security.infrastructure.adapter;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * Clase encargada de manejar Token JWT
 */
@Slf4j
public class CustomJWT {
    private final String secrectKey;
    private final long expDateDefined;

    /**
     * Se define la Secrect Key para el Token y la fecha de exp
     */
    public CustomJWT() {
        this.secrectKey = "$argon2id$v=19$m=2048,t=2,p=1$F7XsIVx3YSVL6tGdyeGyrA$dLXD9Clq4po8/dL6b0IudGmgGyr+4cHNTM4fjqG5LDw";
        this.expDateDefined = 14400000;
    }

    /**
     * Recupera el subject del Token
     *
     * @param token Token salido del header AUTHORIZATION
     * @return Subject - en este caso es el Email
     */
    public String createUserByToken(String token) {
        var decodedJWT = createDecoder(token);
        return decodedJWT.getSubject();
    }

    public String token(String bearerToken){
        return verifyToken(bearerToken) ? bearerToken.substring("Bearer ".length()) : null;
    }

    /**
     * Verifica que el Token no este vacio y tenga la marca de Bearer
     */
    private Boolean verifyToken(String auth) {
        return Boolean.TRUE.equals(StringUtils.hasText(auth) && auth.startsWith("Bearer "));
    }

    /**
     * Crea el accessToken
     */
    public String createAccesToken(String email, String url) {
        return com.auth0.jwt.JWT.create()
                .withSubject(email)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + expDateDefined))
                .withIssuer(url)
                .sign(signKey());
    }

    /**
     * Crea el refresh Token
     */
    public String createRefreshToken(String email, String url) {
        return com.auth0.jwt.JWT.create()
                .withSubject(email)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + expDateDefined + 259200000))
                .withIssuer(url)
                .sign(signKey());
    }

    /**
     * Crea el algoritmo para decodificar el token
     */
    public Algorithm signKey() {
        return Algorithm.HMAC256(secrectKey.getBytes());
    }

    /**
     * Crea el token decodificado
     */
    public DecodedJWT createDecoder(String token) {
        return com.auth0.jwt.JWT.require(this.signKey()).build().verify(token);
    }

}
