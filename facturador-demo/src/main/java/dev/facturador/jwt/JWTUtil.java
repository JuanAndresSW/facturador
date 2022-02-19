package dev.facturador.jwt;

import dev.facturador.dto.security.CustomUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.Serializable;
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
@RequiredArgsConstructor
public class JWTUtil implements Serializable {

    private static final long serialVersionUID = -3301605591108950415L;

    @Value("${security.jwt.secret}")
    private String key;
    @Value("${security.jwt.ttlMillis}")
    private long ttlMillis;
    private Clock clock = DefaultClock.INSTANCE;

    /**
     * Genera el token
     * @param userDetails De este objeto saca el {username, id, rol} del usuario
     * @return
     */
    public String generateToken(CustomUserDetails userDetails) {
        String subject = userDetails.getUsername();
        String id = String.valueOf(userDetails.getId());
        String rol = userDetails.getAuthorities().stream().toList().get(0).getAuthority();
        //Compacta el JWT a un String seguro
        return Jwts.builder()
                .setId(id)
                .setIssuedAt(clock.now())
                .setSubject(subject)
                .claim("ROL", rol)
                .setExpiration(this.calculateExpirationDate(clock.now()))
                .signWith(SignatureAlgorithm.HS256, convertSecrectToKey()).compact();
    }

    public Key convertSecrectToKey(){
        var signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] keyByte = DatatypeConverter.parseBase64Binary(key);
        return new SecretKeySpec(keyByte, signatureAlgorithm.getJcaName());
    }

    /**
     * Genera el timepo de expiracion del token
     * @return
     */
    public Date calculateExpirationDate(Date created){
        return new Date(created.getTime() +ttlMillis);
    }

    /**
     * Devuelve el username del usuario
     * @param jwt Token recibido
     * @return
     */
    public String getValue(String jwt) {
        return Jwts.parser().setSigningKey(convertSecrectToKey()).parseClaimsJws(jwt)
                .getBody().getSubject();
    }

    /**
     * Devuelve el Rol del usuario guardado en el token
     * @param jwt Token recibido
     * @return
     */
    public String getRol(String jwt) {
        return (String) Jwts.parser().setSigningKey(convertSecrectToKey()).parseClaimsJws(jwt)
                .getBody().get("ROL");
    }

    /**
     * Devuelve la key del token
     * en este caso la key es el id del Usuario
     * @param jwt Token recibido
     * @return
     */
    public String getKey(String jwt) {
        return Jwts.parser().setSigningKey(convertSecrectToKey()).parseClaimsJws(jwt)
                .getBody().getId();
    }

    /**
     * Comprueba si el token ha expirado
     * @param jwt Token recibido
     * @return
     */
    public Boolean hasTokenExpirated(String jwt){
        return Jwts.parser().setSigningKey(convertSecrectToKey()).parseClaimsJws(jwt)
                .getBody().getExpiration().before(clock.now());
    }

    /**
     * Comprueba si el token arroja una excepcion
     * En caso de excepcion se informa en el log que es lo que falla
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

    public boolean validatTokenWith(String token, CustomUserDetails userDetails){
        final String username = this.getValue(token);
        return (username.equals(userDetails.getUsername()) && !hasTokenExpirated(token));
    }

    public String refreshToken(String token){
        final Date createdDate = clock.now();
        final Date expirationDate = this.calculateExpirationDate(createdDate);

        return Jwts.builder()
                .setId(this.getKey(token))
                .setIssuedAt(createdDate)
                .setSubject(this.getValue(token))
                .claim("ROL", this.getRol(token))
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, convertSecrectToKey()).compact();
    }
}
