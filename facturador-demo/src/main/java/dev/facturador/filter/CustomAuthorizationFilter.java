package dev.facturador.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.facturador.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    private JWTUtil jwt;

    public CustomAuthorizationFilter(JWTUtil jwt) {
        this.jwt = jwt;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //Si es algunas de estas rutas no necesita autorizacion solo esta intentando iniciar o registrarse
        if (isNotRequiredAuthorization(request)) {
            filterChain.doFilter(request, response);
        }
        //De cualquier otra ruta que no sean esas si se comprueba la autorizacion
        if (isRequiredAuthorization(request)) {
            String authHeader = request.getHeader(AUTHORIZATION);
            if (jwt.verifyAuthToken(authHeader)) {
                try {
                    String token = authHeader.substring("Bearer ".length());
                    var authUser = createUserAuthorizedWithToken(token);
                    SecurityContextHolder.getContext().setAuthentication(authUser);
                    filterChain.doFilter(request, response);
                } catch (Exception ex) {
                    var error = propagatingErrorMessage(response, ex);
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }

            } else {
                filterChain.doFilter(request, response);
            }

        }
    }

    private static Map<String, String> propagatingErrorMessage(HttpServletResponse response, Exception ex) {
        log.error("Error logging in: {}", ex.getMessage());
        response.setHeader("error", ex.getMessage());
        response.setStatus(FORBIDDEN.value());
        Map<String, String> error = new HashMap<>();
        error.put("error-message", ex.getMessage());
        response.setContentType(APPLICATION_JSON_VALUE);
        return error;
    }

    private UsernamePasswordAuthenticationToken createUserAuthorizedWithToken(String token) {
        DecodedJWT decodedJWT = jwt.createDecoder(token);
        String username = jwt.getSubject(decodedJWT);
        String rol = jwt.getClaimRol(decodedJWT);

        Collection<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(rol));
        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }

    /**
     * Logica para saber si no es necesario el filtro
     */
    public static boolean isNotRequiredAuthorization(HttpServletRequest request) {
        return request.getServletPath().equals("/login") || request.getServletPath().equals("/api/auth/login") || request.getServletPath().equals("/api/auth/main/signup");
    }

    /**
     * Logica para saber si es necesario el filtro
     */
    public static boolean isRequiredAuthorization(HttpServletRequest request) {
        return !request.getServletPath().equals("/login") && !request.getServletPath().equals("/api/auth/login") && !request.getServletPath().equals("/api/auth/main/signup");
    }

}
