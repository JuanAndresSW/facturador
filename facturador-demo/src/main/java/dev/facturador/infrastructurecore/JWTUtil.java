package dev.facturador.infrastructurecore;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import javax.xml.bind.DatatypeConverter;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

@Slf4j
@RequiredArgsConstructor
public class JWTUtil {
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
     * Crea el {@code Access Token}
     *
     * @param username Username dueño del token
     * @param rol      Rol del usuario
     * @param url      {@code URL} desde donde se crea
     * @return Token {@link JWT} en forma de String compacto
     */
    public String createAccesToken(String username, String rol, String url) {
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + expDateDefined))
                .withIssuer(url)
                .withClaim("rol", rol)
                .sign(signKey());
    }

    /**
     * Crea el {@code Refresh Token}
     *
     * @param username Username dueño del token
     * @param url      {@code URL} desde donde se crea
     * @return Token {@link JWT} en forma de String compacto
     */
    public String createRefreshToken(String username, String rol, String url) {
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + expDateDefined + 14400000))
                .withClaim("rol", rol)
                .withIssuer(url)
                .sign(signKey());
    }

    /**
     * Crea el {@link Algorithm} con el que se cifra el Token
     * <br/>
     * Tambien sirve para el {@link JWTVerifier}  del Token
     *
     * @return {@link Algorithm} de cifrado y des-cifrado del Token
     */
    public Algorithm signKey() {
        return Algorithm.HMAC256(DatatypeConverter.parseBase64Binary(secrectKey));
    }

    /**
     * Verifica que el Token sea Bearer
     *
     * @param auth El token a autenticar
     * @return {@code Boolean}
     */
    public boolean verifyAuthToken(String auth) {
        return StringUtils.hasText(auth) && auth.startsWith("Bearer ");
    }

    /**
     * Construye el {@link JWTVerifier} <br/>
     * Y con el {@code token} creo el {@link DecodedJWT}
     *
     * @param token El {@code token} que se quiere decodificar
     * @return {@link DecodedJWT} Decodificador del {@code token}
     */
    public DecodedJWT createDecoder(String token) {
        return JWT.require(this.signKey()).build().verify(token);
    }

    /**
     * Retorna el Subject del token
     *
     * @param decodedJWT {@link DecodedJWT} del token
     * @return String
     */
    public String getSubject(DecodedJWT decodedJWT) {
        return decodedJWT.getSubject();
    }

    /**
     * Recupero el {@link Claim} {@code "rol"} del Token
     * <br/>
     * Y lo retorno como String
     *
     * @param decodedJWT {@link DecodedJWT} del token
     * @return String
     */
    public String getClaimRol(DecodedJWT decodedJWT) {
        return decodedJWT.getClaim("rol").asString();
    }

    public UsernamePasswordAuthenticationToken createUserByToken(String authHeader) throws Exception {
        String token = authHeader.substring("Bearer ".length());
        var decodedJWT = createDecoder(token);
        String username = getSubject(decodedJWT);
        String rol = getClaimRol(decodedJWT);
        Collection<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(rol));
        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }
}
