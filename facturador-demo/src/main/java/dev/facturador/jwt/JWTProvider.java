package dev.facturador.jwt;

import dev.facturador.dto.security.CustomUserDetails;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * Clase de utilidad para el token
 * Genera el Token
 * Puede revisar que no tenga excepciones el Token
 * Recupera valores del Token
 */
@Component
@Slf4j
public class JWTProvider {
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
    public String generateToken(CustomUserDetails userDetails) {
        //La forma de autenticacion
        String subject = userDetails.getUsername();
        String id = String.valueOf(userDetails.getId());
        //El Token se crea con el Algoritmo HS256
        var signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();

        //Transforma la Key byte en Base64, para usar la ApiKey
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Date exp = null;
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            exp = new Date(expMillis);
        }

        //Compacta el JWT a un String seguro
        return Jwts.builder()
                .setId(id).setIssuedAt(new Date(nowMillis)).setSubject(subject)
                .claim("ROL", userDetails.getAuthorities().stream().toList().get(0).getAuthority())
                .setIssuer(issuer).signWith(SignatureAlgorithm.HS256, signingKey)
                .setExpiration(exp).compact();
    }

    /**
     * Devulve el Value/Subject del token, en neustro es el username
     */
    public String getValue(String jwt) {
        // Recupera el JWT, si no es correcto arroja una excepcion
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key)).parseClaimsJws(jwt)
                .getBody().getSubject();
    }

    /**
     * Devulve el claim (En este caso solo le indique uno el cual es el Rol)
     */
    public String getRol(String jwt) {
        // Recupera el JWT, si no es correcto arroja una excepcion
        return (String) Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key)).parseClaimsJws(jwt)
                .getBody().get("ROL");
    }

    /**
     * Devulve el Key/Id del token (En este caso es el id del usuario)
     */
    public String getKey(String jwt) {
        // Recupera el JWT, si no es correcto arroja una excepcion
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key)).parseClaimsJws(jwt)
                .getBody().getId();
    }

    public Boolean hasTokenExpirated(String jwt){
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key)).parseClaimsJws(jwt)
                .getBody().getExpiration().before(new Date());
    }

    /**
     * Comrpueba que no tenga excepcines el token
     */
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
