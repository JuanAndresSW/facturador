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

    @Value("${security.jwt.secret}")
    private String key;

    @Value("${security.jwt.issuer}")
    private String issuer;

    @Value("${security.jwt.ttlMillis}")
    private long ttlMillis;

    /**
     * Genera el token
     * @param userDetails De este objeto saca el {username, id, rol} del usuario
     * @return
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

        //Compacta el JWT a un String seguro
        return Jwts.builder()
                .setId(id).setIssuedAt(new Date(nowMillis)).setSubject(subject)
                .claim("ROL", userDetails.getAuthorities().stream().toList().get(0).getAuthority())
                //Este campo indica quien creo el token en este caso no lo manejamo pero lo agrego igual
                .setIssuer(issuer).signWith(SignatureAlgorithm.HS256, signingKey)
                .setExpiration(generateExpirationDate(nowMillis)).compact();
    }

    /**
     * Genera el timepo de expiracion del token
     * @param nowMillis Recupera el milisegundo capturado ahora
     * @return
     */
    public Date generateExpirationDate(long nowMillis){
        long expMillis = 0;
        if (ttlMillis >= 0) {
            expMillis = nowMillis + ttlMillis;
        }
        return new Date(expMillis);
    }

    /**
     * Devuelve el username del usuario
     * @param jwt Token recibido
     * @return
     */
    public String getValue(String jwt) {
        // Recupera el JWT, si no es correcto arroja una excepcion
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key)).parseClaimsJws(jwt)
                .getBody().getSubject();
    }

    /**
     * Devuelve el Rol del usuario guardado en el token
     * @param jwt Token recibido
     * @return
     */
    public String getRol(String jwt) {
        // Recupera el JWT, si no es correcto arroja una excepcion
        return (String) Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key)).parseClaimsJws(jwt)
                .getBody().get("ROL");
    }

    /**
     * Devuelve la key del token
     * en este caso la key es el id del Usuario
     * @param jwt Token recibido
     * @return
     */
    public String getKey(String jwt) {
        // Recupera el JWT, si no es correcto arroja una excepcion
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key)).parseClaimsJws(jwt)
                .getBody().getId();
    }

    /**
     * Comprueba qeu el token no halla expirado
     * @param jwt Token recibido
     * @return
     */
    public Boolean hasTokenExpirated(String jwt){
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key)).parseClaimsJws(jwt)
                .getBody().getExpiration().before(new Date());
    }

    /**
     * Comprueba si el token arroja una excepcion
     * En caso de excepcion se informa en el log que es lo qeu falla
     * @param token Token recibido
     * @return True si es correcto, false en caso de excepcion
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
