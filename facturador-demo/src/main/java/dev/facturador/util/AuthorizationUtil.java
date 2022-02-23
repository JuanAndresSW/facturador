package dev.facturador.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;


@Component
@RequiredArgsConstructor
public final class AuthorizationUtil {

    /**
     * Este metodo crea el UsernamePasswordAuthenticationToken este es una clase proporcionada por Spring
     * Este crea un usuario segun los datos del Token
     *
     * @param token Token con los datos
     * @param jwt   Utileria para llamar metodos del JWT
     */
    public static UsernamePasswordAuthenticationToken tokenAuthorizedUser(String token, JWTUtil jwt) {
        JWTVerifier verifier = JWT.require(jwt.signKey()).build();
        DecodedJWT decodedJWT = verifier.verify(token);

        String username = jwt.getSubject(decodedJWT);
        String rol = jwt.getClaimRol(decodedJWT);
        Collection<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(rol));
        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }

    /**
     * Logica para saber si no es necesario el filtro
     */
    public static boolean checkIfAuthorizationIsNotRequired(HttpServletRequest request) {
        return request.getServletPath().equals("/login") || request.getServletPath().equals("/api/auth/login") || request.getServletPath().equals("/api/auth/main/signup");
    }

    /**
     * Logica para saber si es necesario el filtro
     */
    public static boolean checkIfAuthorizationIsRequired(HttpServletRequest request) {
        return !request.getServletPath().equals("/login") && !request.getServletPath().equals("/api/auth/login") && !request.getServletPath().equals("/api/auth/main/signup");
    }

}
