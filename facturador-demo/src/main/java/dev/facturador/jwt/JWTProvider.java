package dev.facturador.jwt;


import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * Esta clase genera el token y permite recuperar sus valores
 */
@Component
@Slf4j
public final class JWTProvider {
    //Value le asigna un valor que indico en el archivo .properties (En este caso .yml)
    //Usando Expresion Language
    @Value("${security.jwt.secret}")
    private String key;

    @Value("${security.jwt.issuer}")
    private String issuer;

    @Value("${security.jwt.ttlMillis}")
    private long ttlMillis;

    /**
     * Crea un nuevo Token
     */
    public String generateToekn(String id, Authentication authentication) {
        //La forma de autenticacion
        String subject = authentication.getName();
        //El Token se crea con el Algoritmo HS256
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();

        //Transforma la Key a un byte[], para usar la ApiKey
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // Builder de JWT
        JwtBuilder builder = Jwts.builder()
                .setId(id).setIssuedAt(new Date(nowMillis)).setSubject(subject).setIssuer(issuer)
                .signWith(SignatureAlgorithm.HS256, signingKey);

        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        // Construye el JWT, luego lo compacta a un String seguro
        return builder.compact();
    }

    /**
     * Metodo para valida y leer el JWT
     */
    public String getValue(String jwt) {
        // Recupera el JWT, si no es correcto arroja una excepcion
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key))
                .parseClaimsJws(jwt).getBody().getSubject();
    }

    /**
     * Metodo para valida y leer el JWT
     */
    public String getKey(String jwt) {
        // Recupera el JWT, si no es correcto arroja una excepcion
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key))
                .parseClaimsJws(jwt).getBody().getId();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key)).parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException e){
            log.error("token mal formado");
        }catch (UnsupportedJwtException e){
            log.error("token no soportado");
        }catch (ExpiredJwtException e){
            log.error("token expirado");
        }catch (IllegalArgumentException e){
            log.error("token vacío");
        }catch (SignatureException e){
            log.error("fail en la firma");
        }
        return false;
    }
}
