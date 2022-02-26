package dev.facturador.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import dev.facturador.dto.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;

@Component
@RequiredArgsConstructor
public final class JWTUtil {
    private final String secrectKey;
    private final long expDateDefined;

    /**
     * Inicio los datos para el token
     */
    public JWTUtil() {
        this.secrectKey = "$argon2id$v=19$m=2048,t=2,p=1$F7XsIVx3YSVL6tGdyeGyrA$dLXD9Clq4po8/dL6b0IudGmgGyr+4cHNTM4fjqG5LDw";
        //Este valor dice que la sesion dura dos dias (Para sacarlo "horas * 3 600 000")
        this.expDateDefined = 172800000;
    }

    /**
     * Crea el Token de Acceso
     *
     * @param user Usuario que crea el Token
     * @param url  URL desde donde se crea este token
     */
    public String createAccesToken(CustomUserDetails user, String url) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + expDateDefined))
                .withIssuer(url)
                .withClaim("rol", user.getAuthorities().stream().toList().get(0).getAuthority())
                .sign(signKey());
    }

    /**
     * Crea el token de Refresh
     *
     * @param user Usuario que la crea
     * @param url  URL desde donde se crea
     */
    public String createRefreshToken(CustomUserDetails user, String url) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + expDateDefined + 14400000))
                .withIssuer(url)
                .sign(signKey());
    }

    /**
     * @return Devuelve el Algoritmo utilizado para firmar el token
     */
    public Algorithm signKey() {
        return Algorithm.HMAC256(DatatypeConverter.parseBase64Binary(secrectKey));
    }

    /**
     * @return Comprueba que el Bearer token este presente
     */
    public boolean verifyAuthToken(String auth) {
        return StringUtils.hasText(auth) && auth.startsWith("Bearer ");
    }

    /**
     * @param token Token a decodificar
     * @return Retorna el decodificador del token de este podes sacar los valor del token
     */
    public DecodedJWT createDecoder(String token) {
        return JWT.require(this.signKey()).build().verify(token);
    }

    /**
     * @param decodedJWT Decoder del token
     * @return Retorna el subject asignado en este es el username
     */
    public String getSubject(DecodedJWT decodedJWT) {
        return decodedJWT.getSubject();
    }

    /**
     * @param decodedJWT Decoder del token
     * @return Retorna un claim del rol
     */
    public String getClaimRol(DecodedJWT decodedJWT) {
        return decodedJWT.getClaim("rol").asString();
    }
}
